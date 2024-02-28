package br.com.fiap.parkingmeterbr.repository;

import br.com.fiap.parkingmeterbr.model.Parkingmeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ParkingmeterRepository extends JpaRepository<Parkingmeter, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tbparkingmeters (code, street, number, zipcode, neighborhood, city, uf, dt_create)" +
            "values (:code, :street, :number, :zipcode, :neighborhood, :city, :uf, :dtCreate)", nativeQuery = true)
    Object createParkingmeter(@Param("code") String code, @Param("street") String street, @Param("number") Integer number,
                      @Param("zipcode") String zipcode, @Param("neighborhood") String neighborhood, @Param("city") String city,
                      @Param("uf") String uf, @Param("dtCreate") LocalDateTime dtCreate);

    Optional<Parkingmeter> findByCode(String code);

}
