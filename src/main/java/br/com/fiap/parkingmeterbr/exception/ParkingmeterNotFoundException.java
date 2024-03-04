package br.com.fiap.parkingmeterbr.exception;

public class ParkingmeterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ParkingmeterNotFoundException(String message) {
		super(message);
	}

}
