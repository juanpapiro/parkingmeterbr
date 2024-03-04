package br.com.fiap.parkingmeterbr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parkingmeterbr.controller.swagger.ParkingmeterControllerOpenApi;
import br.com.fiap.parkingmeterbr.dto.AddressDto;
import br.com.fiap.parkingmeterbr.dto.PageParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterRequest;
import br.com.fiap.parkingmeterbr.service.ParkingmeterService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/parkingmeter")
public class ParkingmeterController implements ParkingmeterControllerOpenApi {

    @Autowired
    private ParkingmeterService parkingmeterService;

    @GetMapping
    public ResponseEntity<PageParkingmeterDto> findAll(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(parkingmeterService.findAllParkingmeters(pageable));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ParkingmeterDto> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(parkingmeterService.findByCode(code));
    }

    @CacheEvict(value = "parkingmeter", allEntries = true)
    @PostMapping
    public ResponseEntity<ParkingmeterDto> createParkingmeter(
            @Valid @RequestBody ParkingmeterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body(parkingmeterService.createParkingmeter(request));
    }

    @GetMapping("/address")
    public ResponseEntity<AddressDto> findAddressByCep(@RequestParam(name = "cep") String cep) {
        return ResponseEntity.ok(parkingmeterService.findAddressByCep(cep));
    }

}
