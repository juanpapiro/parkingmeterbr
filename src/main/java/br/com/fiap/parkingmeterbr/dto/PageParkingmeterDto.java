package br.com.fiap.parkingmeterbr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageParkingmeterDto {

    private List<ParkingmeterDto> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;

}
