package br.com.fiap.parkingmeterbr.controller;

import br.com.fiap.parkingmeterbr.controller.config.swagger.ParkingmeterControllerOpenApi;
import br.com.fiap.parkingmeterbr.dto.PageParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterRequest;
import br.com.fiap.parkingmeterbr.service.ParkingmeterService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingmeter")
public class ParkingmeterController implements ParkingmeterControllerOpenApi {

    @Autowired
    private ParkingmeterService parkingmeterService;

    @Cacheable(value = "parkingmeter")
    @GetMapping
    public ResponseEntity<PageParkingmeterDto> findAll(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return parkingmeterService.findAllParkingmeters(pageable);
    }

    @Cacheable(value = "parkingmeter")
    @GetMapping("/{code}")
    public ResponseEntity<ParkingmeterDto> findByCode(@PathVariable String code) {
        return parkingmeterService.findByCode(code);
    }

    @CacheEvict(value = "parkingmeter", allEntries = true)
    @PostMapping
    public ResponseEntity<ParkingmeterDto> createParkingmeter(
            @Valid @RequestBody ParkingmeterRequest request) {
        return parkingmeterService.createParkingmeter(request);
    }

}
