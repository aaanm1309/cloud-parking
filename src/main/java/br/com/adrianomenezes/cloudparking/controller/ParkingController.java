package br.com.adrianomenezes.cloudparking.controller;

import br.com.adrianomenezes.cloudparking.controller.dto.ParkingCreateDTO;
import br.com.adrianomenezes.cloudparking.controller.dto.ParkingDTO;
import br.com.adrianomenezes.cloudparking.controller.mapper.ParkingMapper;
import br.com.adrianomenezes.cloudparking.entity.Parking;
import br.com.adrianomenezes.cloudparking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/{id}")
    public ResponseEntity deletedById(@PathVariable String id){

        parkingService.deleteById(id);
        return ResponseEntity.noContent().build();

    }
    @Operation(summary = "Create Parking", description = "Api to create a Parking")
//    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\n" + "  \"username\": \"jane\",\n"
//            + "  \"password\": \"password\"\n" + "}", summary = "Parking Example")))
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){

        Parking parking = parkingMapper.toParkingCreate(dto);
        ParkingDTO result =  parkingMapper.toParkingDTO(parkingService.create(parking));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id,@RequestBody ParkingCreateDTO dto){
        Parking parking = parkingMapper.toParkingCreate(dto);
        parking.setId(id);
        ParkingDTO result =  parkingMapper.toParkingDTO(parkingService.update(parking));
        return ResponseEntity.ok(result);

    }

    @PostMapping("/{id}")
    public ResponseEntity<ParkingDTO> exitParking(@PathVariable String id){
        ParkingDTO result =  parkingMapper.toParkingDTO(parkingService.exitParking(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }
}
