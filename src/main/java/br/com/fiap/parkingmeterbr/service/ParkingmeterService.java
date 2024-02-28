package br.com.fiap.parkingmeterbr.service;

import br.com.fiap.parkingmeterbr.dto.PageParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParkingmeterService {

    ResponseEntity<PageParkingmeterDto> findAllParkingmeters(Pageable pageable);

    ResponseEntity<ParkingmeterDto> findByCode(String code);
    ResponseEntity<ParkingmeterDto> createParkingmeter(ParkingmeterRequest request);

}
