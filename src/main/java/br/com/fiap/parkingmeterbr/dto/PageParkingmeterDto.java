package br.com.fiap.parkingmeterbr.dto;

import java.util.List;

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
public class PageParkingmeterDto {

    private List<ParkingmeterDto> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;

}
