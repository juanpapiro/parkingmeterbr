package br.com.fiap.parkingmeterbr.dto;

import br.com.fiap.parkingmeterbr.model.Parkingmeter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingmeterRequest {

    @NotBlank
    private String street;

    @NotNull
    private Integer number;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String city;

    @NotBlank
    private String uf;

    @NotBlank
    @Pattern(regexp = "(\\d{5})-(\\d{3})", message = "Formato de cep incorreto")
    private String zipcode;

    public Parkingmeter toEntity() {
        Parkingmeter parkingmeter = new Parkingmeter();
        BeanUtils.copyProperties(this, parkingmeter);
        parkingmeter.setStreet(parkingmeter.getStreet().toUpperCase());
        parkingmeter.setUf(parkingmeter.getUf().toUpperCase());
        parkingmeter.setCity(parkingmeter.getCity().toUpperCase());
        parkingmeter.setNeighborhood(parkingmeter.getNeighborhood().toUpperCase());
        return parkingmeter;
    }

}
