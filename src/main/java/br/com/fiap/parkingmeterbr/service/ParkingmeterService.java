package br.com.fiap.parkingmeterbr.service;

import org.springframework.data.domain.Pageable;

import br.com.fiap.parkingmeterbr.dto.AddressDto;
import br.com.fiap.parkingmeterbr.dto.PageParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterRequest;

public interface ParkingmeterService {

    PageParkingmeterDto findAllParkingmeters(Pageable pageable);

    ParkingmeterDto findByCode(String code);
        
    ParkingmeterDto createParkingmeter(ParkingmeterRequest request);

    AddressDto findAddressByCep(String cep);

}
