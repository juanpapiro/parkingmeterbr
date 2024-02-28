package br.com.fiap.parkingmeterbr.dto;

import br.com.fiap.parkingmeterbr.model.Parkingmeter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingmeterDto extends ParkingmeterRequest {

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
