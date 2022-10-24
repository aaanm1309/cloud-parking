package br.com.adrianomenezes.cloudparking.controller;

import br.com.adrianomenezes.cloudparking.controller.dto.ParkingDTO;
import br.com.adrianomenezes.cloudparking.controller.mapper.ParkingMapper;
import br.com.adrianomenezes.cloudparking.entity.Parking;
import br.com.adrianomenezes.cloudparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {


    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }


    @GetMapping
    public List<ParkingDTO> findAll(){
//        var parking = new Parking();
//        parking.setColor("PRETO");
//        parking.setLicense("MMM-1232");
//        parking.setModel("GOL");
//        parking.setState("SP");
//        var parking2 = new Parking();
//        parking.setColor("VERDE");
//        parking.setLicense("MMM-3333");
//        parking.setModel("VECTRA");
//        parking.setState("MG");
//
//        return Arrays.asList(parking,parking2);

        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result =  parkingMapper.toParkingDTOList(parkingList);
        return result;

    }
}
