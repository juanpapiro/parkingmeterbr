package br.com.fiap.parkingmeterbr.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.parkingmeterbr.dto.AddressDto;
import br.com.fiap.parkingmeterbr.dto.PageParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterRequest;
import br.com.fiap.parkingmeterbr.exception.ParkingmeterNotFoundException;
import br.com.fiap.parkingmeterbr.model.Parkingmeter;
import br.com.fiap.parkingmeterbr.repository.ParkingmeterRepository;
import br.com.fiap.parkingmeterbr.service.CepService;
import br.com.fiap.parkingmeterbr.service.ParkingmeterService;

@Service
public class ParkingmeterServiceImpl implements ParkingmeterService {

    @Autowired
    private ParkingmeterRepository parkingmeterRepository;

    @Autowired
    private CepService cepService;

    
    @Cacheable(value = "parkingmeter")
    @Override
    public PageParkingmeterDto findAllParkingmeters(Pageable pageable) {
        Page<Parkingmeter> parkingmeters = parkingmeterRepository.findAll(pageable);
        return Optional.ofNullable(parkingmeters)
                .filter(page -> !page.isEmpty())
                .map(page -> ParkingmeterDto.toDtoList(page))
                .orElseThrow(() -> new ParkingmeterNotFoundException("Nenhum parquímetro localizado."));
    }

 
    @Cacheable(value = "parkingmeter")
    @Override
    public ParkingmeterDto findByCode(String code) {
    	return parkingmeterRepository.findByCode(code)
    			.map(ParkingmeterDto::toDto)
    			.orElseThrow(() -> new ParkingmeterNotFoundException("Parquímetro não localizado."));
    }


    @Override
    public ParkingmeterDto createParkingmeter(ParkingmeterRequest request) {
        Parkingmeter parkingmeter = request.toEntity();
        parkingmeter.buildCode();
        parkingmeter.setDtCreate(LocalDateTime.now());
        Integer result = parkingmeterRepository.createParkingmeter(parkingmeter.getCode(),
                parkingmeter.getStreet(), parkingmeter.getNumber(), parkingmeter.getZipcode(),
                parkingmeter.getNeighborhood(), parkingmeter.getCity(), parkingmeter.getUf(), parkingmeter.getDtCreate());
        return Optional.ofNullable(result)
        		.filter(rs -> rs.intValue() == 1)
        		.map(rs -> ParkingmeterDto.toDto(parkingmeter))
        		.orElseThrow(() -> new RuntimeException(""));
    }

    
    @Override
    public AddressDto findAddressByCep(String cep) {
        return cepService.findAdrress(cep);
    }



}
