package br.com.fiap.parkingmeterbr.service.impl;

import br.com.fiap.parkingmeterbr.dto.AddressDto;
import br.com.fiap.parkingmeterbr.exception.ParkingmeterNotFoundException;
import br.com.fiap.parkingmeterbr.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class CepServiceImpl implements CepService {

    @Autowired
    @Qualifier("withssl")
    private RestTemplate restTemplate;

    @Value("${URL_FIND_ADDRESS_BY_CEP}")
    private String urlFindAddressByCep;


    @Override
    public AddressDto findAdrress(String cep) {
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(urlFindAddressByCep);
            URI uri = uriBuilder.build(cep);
            ResponseEntity<AddressDto> response = restTemplate.getForEntity(uri.toString(), AddressDto.class);
            return Optional.ofNullable(response).map(ResponseEntity::getBody).orElseThrow(() -> new RuntimeException(""));
        } catch(HttpClientErrorException.NotFound e) {
            throw new ParkingmeterNotFoundException("Cep não localizado!");
        }
    }
}
