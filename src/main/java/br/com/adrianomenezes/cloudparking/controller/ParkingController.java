package br.com.adrianomenezes.cloudparking.controller;

import br.com.adrianomenezes.cloudparking.controller.dto.ParkingCreateDTO;
import br.com.adrianomenezes.cloudparking.controller.dto.ParkingDTO;
import br.com.adrianomenezes.cloudparking.controller.mapper.ParkingMapper;
import br.com.adrianomenezes.cloudparking.entity.Parking;
import br.com.adrianomenezes.cloudparking.service.ParkingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Tag(name = "Parking Controller", description = "API ParkingController")
public class ParkingController {


    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }
    @Operation(summary = "Get list of parking", description = "Get the user details. The operation returns the details of the user that is associated " + "with the provided JWT token.")
    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll(){

        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result =  parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){

        Parking parking = parkingService.findById(id);
        ParkingDTO result =  parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);

    }
    @Operation(summary = "Create Parking", description = "Api to create a Parking")
//    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\n" + "  \"username\": \"jane\",\n"
//            + "  \"password\": \"password\"\n" + "}", summary = "Parking Example")))
    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){

        var parking = parkingMapper.toParkingCreate(dto);
        ParkingDTO result =  parkingMapper.toParkingDTO(parkingService.create(parking));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }
}
