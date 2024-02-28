package br.com.fiap.parkingmeterbr.service.impl;

import br.com.fiap.parkingmeterbr.dto.PageParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterRequest;
import br.com.fiap.parkingmeterbr.model.Parkingmeter;
import br.com.fiap.parkingmeterbr.repository.ParkingmeterRepository;
import br.com.fiap.parkingmeterbr.service.ParkingmeterService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingmeterServiceImpl implements ParkingmeterService {

    @Autowired
    private ParkingmeterRepository parkingmeterRepository;


    @Override
    public ResponseEntity<PageParkingmeterDto> findAllParkingmeters(Pageable pageable) {
        Page<Parkingmeter> parkingmeters = parkingmeterRepository.findAll(pageable);
        return Optional.ofNullable(parkingmeters)
                .filter(page -> !page.isEmpty())
                .map(page -> ParkingmeterDto.toDtoList(page))
                .map(listDto -> ResponseEntity.ok(listDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ParkingmeterDto> findByCode(String code) {
        return parkingmeterRepository.findByCode(code)
                .map(p -> ResponseEntity.ok(ParkingmeterDto.toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }


    @Override
    public ResponseEntity<ParkingmeterDto> createParkingmeter(ParkingmeterRequest request) {
        Parkingmeter parkingmeter = request.toEntity();
        parkingmeter.buildCode();
        parkingmeter.setDtCreate(LocalDateTime.now());
        Object result = parkingmeterRepository.createParkingmeter(parkingmeter.getCode(),
                parkingmeter.getStreet(), parkingmeter.getNumber(), parkingmeter.getZipcode(),
                parkingmeter.getNeighborhood(), parkingmeter.getCity(), parkingmeter.getUf(), parkingmeter.getDtCreate());
        return ResponseEntity.status(HttpStatus.CREATED).body(ParkingmeterDto.toDto(parkingmeter));
    }


    private String buildHash(Parkingmeter parkingmeter) {
        StringBuilder sb = new StringBuilder(parkingmeter.getStreet())
                .append(parkingmeter.getZipcode())
                .append(String.valueOf(parkingmeter.getNumber()));
        return Hashing.hmacMd5(sb.toString().getBytes(StandardCharsets.UTF_8))
                .hashString(sb.toString(), StandardCharsets.UTF_8).toString();
    }
}
