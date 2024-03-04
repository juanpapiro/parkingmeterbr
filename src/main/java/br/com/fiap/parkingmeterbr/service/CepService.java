package br.com.fiap.parkingmeterbr.service;

import br.com.fiap.parkingmeterbr.dto.AddressDto;

public interface CepService {

    AddressDto findAdrress(String cep);

}
