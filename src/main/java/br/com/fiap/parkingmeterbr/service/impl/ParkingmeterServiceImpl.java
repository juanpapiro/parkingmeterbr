package br.com.fiap.parkingmeterbr.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
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

@Log4j2
@Service
public class ParkingmeterServiceImpl implements ParkingmeterService {

    @Autowired
    private ParkingmeterRepository parkingmeterRepository;

    @Autowired
    private CepService cepService;

    
    @Cacheable(value = "parkingmeter")
    @Override
    public PageParkingmeterDto findAllParkingmeters(Pageable pageable) {
    	log.info("Buscando parquimetros: {}", pageable);
        Page<Parkingmeter> parkingmeters = parkingmeterRepository.findAll(pageable);
        return Optional.ofNullable(parkingmeters)
                .filter(page -> !page.isEmpty())
                .map(page -> {
                	log.info("Parquimetros localizados: {}", page.getTotalElements());
                	return ParkingmeterDto.toDtoList(page);
                })
                .orElseThrow(() -> new ParkingmeterNotFoundException("Nenhum parquímetro localizado."));
    }

 
    @Cacheable(value = "parkingmeter")
    @Override
    public ParkingmeterDto findByCode(String code) {
    	log.info("Buscando parquimetro: {}", code);
    	return parkingmeterRepository.findByCode(code)
    			.map(parqkingmeter -> {
    				log.info("Parquimetro localizado: {}", parqkingmeter);
    				return ParkingmeterDto.toDto(parqkingmeter);
    			})
    			.orElseThrow(() -> new ParkingmeterNotFoundException("Parquímetro não localizado."));
    }


    @Override
    public ParkingmeterDto createParkingmeter(ParkingmeterRequest request) {
        Parkingmeter parkingmeter = request.toEntity();
        parkingmeter.buildCode();
        parkingmeter.setDtCreate(LocalDateTime.now());
        log.info("*** Criando parquimetro: {}", parkingmeter);
        Integer result = parkingmeterRepository.createParkingmeter(parkingmeter.getCode(),
                parkingmeter.getStreet(), parkingmeter.getNumber(), parkingmeter.getZipcode(),
                parkingmeter.getNeighborhood(), parkingmeter.getCity(), parkingmeter.getUf(), parkingmeter.getDtCreate());
        log.info("*** Parquimetro criado: {}", parkingmeter);
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
