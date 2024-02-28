package br.com.fiap.parkingmeterbr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ParkingmeterbrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingmeterbrApplication.class, args);
	}

}
