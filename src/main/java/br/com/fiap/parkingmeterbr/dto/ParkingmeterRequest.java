package br.com.fiap.parkingmeterbr.dto;

import br.com.fiap.parkingmeterbr.model.Parkingmeter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Schema(name = "street", type = "String", description = "Nome da rua", requiredMode = Schema.RequiredMode.AUTO ,example = "Rua Leblon")
    @NotBlank
    @Size(max = 255)
    private String street;

    @Schema(name = "number", type = "Integer", description = "Número na rua", requiredMode = Schema.RequiredMode.AUTO ,example = "10")
    @NotNull
    private Integer number;

    @Schema(name = "neighborhood", type = "String", description = "Nome do bairro", requiredMode = Schema.RequiredMode.AUTO ,example = "Jardim São Vicente")
    @NotBlank
    @Size(max = 255)
    private String neighborhood;

    @Schema(name = "city", type = "Cidade", description = "Nome da cidade", requiredMode = Schema.RequiredMode.AUTO ,example = "Embu")
    @NotBlank
    @Size(max = 255)
    private String city;

    @Schema(name = "uf", type = "String", description = "Sigla do estado", requiredMode = Schema.RequiredMode.AUTO ,example = "SP")
    @NotBlank
    @Size(max = 255)
    private String uf;

    @Schema(name = "zipcode", type = "String", description = "Cep", requiredMode = Schema.RequiredMode.AUTO ,example = "12345-678")
    @NotBlank
    @Size(max = 9)
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
