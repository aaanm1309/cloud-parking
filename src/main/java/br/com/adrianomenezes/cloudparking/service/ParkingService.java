package br.com.adrianomenezes.cloudparking.service;

import br.com.adrianomenezes.cloudparking.entity.Parking;
import br.com.adrianomenezes.cloudparking.exception.ParkingNotFoundException;
import br.com.adrianomenezes.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Parking findById(String id){
        return parkingRepository.findById(id).orElseThrow(()->
                new ParkingNotFoundException(id)
        );
    }
    @Transactional
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional
    public Parking create(Parking parking) {

        var uuid = getUUID();
        parking.setId(uuid);
        parking.setEntryDate(LocalDateTime.now());

        return parkingRepository.save(parking);
    }

    @Transactional
    public void deleteById(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking update(Parking parking) {
        Parking parkingUpdate = findById(parking.getId());
        if ( parking.getColor() != null && !parking.getColor().isEmpty())
            parkingUpdate.setColor(parking.getColor());
        if (parking.getState() != null && !parking.getState().isEmpty())
            parkingUpdate.setState(parking.getState());
        if (parking.getModel() != null && !parking.getModel().isEmpty())
            parkingUpdate.setModel(parking.getModel());
        if (parking.getLicense() != null && !parking.getLicense().isEmpty())
            parkingUpdate.setLicense(parking.getLicense());
        return parkingRepository.save(parkingUpdate);
    }

    @Transactional
    public Parking exitParking(String id) {
        Parking parkingUpdate = findById(id);
        parkingUpdate.setExitDate(LocalDateTime.now());
//        int timeElapsed =  1 + parkingUpdate.getExitDate().getHour() - parkingUpdate.getEntryDate().getHour();
//        parkingUpdate.setBill((double) (timeElapsed*20));
        parkingUpdate.setBill(ParkingCheckout.getBill(parkingUpdate));
        return parkingRepository.save(parkingUpdate);

    }
}
