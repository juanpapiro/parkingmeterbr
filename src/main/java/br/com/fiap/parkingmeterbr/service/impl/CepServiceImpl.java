package br.com.fiap.parkingmeterbr.service.impl;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.parkingmeterbr.dto.AddressDto;
import br.com.fiap.parkingmeterbr.exception.ParkingmeterNotFoundException;
import br.com.fiap.parkingmeterbr.service.CepService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CepServiceImpl implements CepService {

    @Autowired
    @Qualifier("withssl")
    private RestTemplate restTemplate;

    @Value("${URL_FIND_ADDRESS_BY_CEP}")
    private String urlFindAddressByCep;


    @Override
    public AddressDto findAdrress(String cep) {
    	log.info("Buscando endereço do cep {}", cep);
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(urlFindAddressByCep);
            URI uri = uriBuilder.build(cep);
            ResponseEntity<AddressDto> response = restTemplate.getForEntity(uri.toString(), AddressDto.class);
            return Optional.ofNullable(response).map(resp -> {
            	AddressDto address = resp.getBody();
            	log.info("Endereço localizado: {}", address);
            	return address;
            }).orElseThrow(() -> new RuntimeException(""));
        } catch(HttpClientErrorException.NotFound e) {
            throw new ParkingmeterNotFoundException("Cep não localizado!");
        }
    }
}
