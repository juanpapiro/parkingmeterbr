package br.com.fiap.parkingmeterbr.controller.config.swagger;

import br.com.fiap.parkingmeterbr.dto.ErrorApi;
import br.com.fiap.parkingmeterbr.dto.PageParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterDto;
import br.com.fiap.parkingmeterbr.dto.ParkingmeterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Parquímetros")
public interface ParkingmeterControllerOpenApi {

    @Operation(summary = "Consulta de parquímetros", description = "Liste todos os parquímetros")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = PageParkingmeterDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}
    )
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<PageParkingmeterDto> findAll(Pageable pageable);



    @Operation(summary = "Consulta de parquímetro por código", description = "Consulte um parquímetro")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = List.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}
    )
    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<ParkingmeterDto> findByCode(String code);



    @Operation(summary = "Cadastra parquímetro", description = "Cadastre um novo parquímetro")
    @ApiResponse(
            responseCode = "201",
            content = {@Content(schema = @Schema(implementation = ParkingmeterDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}
    )
    @ApiResponse(
            responseCode = "409",
            content = {@Content(schema = @Schema(implementation = ErrorApi.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}
    )
    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    ResponseEntity<ParkingmeterDto> createParkingmeter(@RequestBody ParkingmeterRequest request);


}
