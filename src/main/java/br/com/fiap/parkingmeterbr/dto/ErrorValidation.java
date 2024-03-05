package br.com.fiap.parkingmeterbr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorValidation {
	
	@Schema(name = "param", type = "String", description = "Parâmetro de request")
	private String param;
	
	@Schema(name = "message", type = "String", description = "Descrição do erro relacionaro ao parâmetro")
	private String message;

}
