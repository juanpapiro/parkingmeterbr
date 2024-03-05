package br.com.fiap.parkingmeterbr.model;

import com.google.common.hash.Hashing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbparkingmeters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(ParkingmeterPK.class)
public class Parkingmeter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Id
    @Column(name = "street", nullable = false)
    private String street;

    @Id
    @Column(name = "number", nullable = false)
    private Integer number;

    @Id
    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "uf", nullable = false)
    private String uf;

    @Column(name = "dt_create", nullable = false)
    private LocalDateTime dtCreate;

    @Column(name = "dt_alter", nullable = true)
    private LocalDateTime dtAlter;



    public void buildCode() {
        StringBuilder sb = new StringBuilder(this.getStreet())
                .append(this.getZipcode())
                .append(String.valueOf(this.getNumber()));
        this.code = Hashing.hmacMd5(sb.toString().getBytes(StandardCharsets.UTF_8))
                .hashString(sb.toString(), StandardCharsets.UTF_8).toString();
    }

}

