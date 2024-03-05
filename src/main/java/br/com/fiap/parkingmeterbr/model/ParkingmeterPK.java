package br.com.fiap.parkingmeterbr.model;

import java.io.Serializable;

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
public class ParkingmeterPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
    private String street;
    private Integer number;
    private String zipcode;

}
