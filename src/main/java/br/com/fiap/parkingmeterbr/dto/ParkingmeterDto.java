package br.com.fiap.parkingmeterbr.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.fiap.parkingmeterbr.model.Parkingmeter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParkingmeterDto extends ParkingmeterRequest implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Schema(name = "code", type = "String", description = "Código do parquímetro", requiredMode = Schema.RequiredMode.AUTO ,example = "f679af614f1810e505df08eac609c16c")
    private String code;


    public static PageParkingmeterDto toDtoList(Page<Parkingmeter> page) {
        List<ParkingmeterDto> results = page.stream().map(ParkingmeterDto::toDto).toList();
        return new PageParkingmeterDto(results, page.getTotalElements(),
                page.getTotalPages(), page.getSize(), page.getNumber());
    }
    public static List<ParkingmeterDto> toDtoList(List<Parkingmeter> parks) {
        return parks.stream().map(ParkingmeterDto::toDto).toList();
    }
    public static ParkingmeterDto toDto(Parkingmeter parkingmeter) {
        ParkingmeterDto dto = new ParkingmeterDto();
        BeanUtils.copyProperties(parkingmeter, dto);
        return dto;
    }

}
