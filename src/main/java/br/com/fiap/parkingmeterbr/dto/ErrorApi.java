package br.com.fiap.parkingmeterbr.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorApi {

	@Schema(name = "message", type = "String", description = "Mensagem de erro")
	private String message;
	
	@Schema(name = "errorsValidation", type = "Lista", format="List<ErrorValidation>", 
			description = "Lista com parâmetros e respectivos erros de request")
	private List<ErrorValidation> errorsValidation;
	
	
	public ErrorApi(String message) {
		this.message = message;
	}
	
	public void setError(ErrorValidation error) {
		if (errorsValidation == null) {
			this.errorsValidation = new ArrayList<>(); 
		}
		errorsValidation.add(error);
	}
	
}
