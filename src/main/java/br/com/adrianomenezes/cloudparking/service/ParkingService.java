package br.com.adrianomenezes.cloudparking.service;

import br.com.adrianomenezes.cloudparking.entity.Parking;
import br.com.adrianomenezes.cloudparking.exception.ParkingNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();
    static {
        var id = getUUID();
        var id2 = getUUID();
        Parking parking = new Parking(id,"DMS-5555","SC","CELTA","PRETO");
        parking.setEntryDate(LocalDateTime.now());
        Parking parking2 = new Parking(id2,"DMS-6666","SP","HRV","LARANJA");
        parking2.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parking);
        parkingMap.put(id2, parking2);

    }
    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    public Parking findById(String id){
        Parking parking = parkingMap.get(id);
        if (parking == null){
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public Parking create(Parking parking) {
        var uuid = getUUID();
        parking.setId(uuid);
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parking);
        return parking;
    }

    public void deleteById(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(Parking parking) {
        Parking parkingUpdate = findById(parking.getId());
        parkingUpdate.setColor(parking.getColor());
        parkingMap.replace(parking.getId(), parkingUpdate);
        return parkingUpdate;
    }

    public Parking exitParking(String id) {
        Parking parkingUpdate = findById(id);
        parkingUpdate.setExitDate(LocalDateTime.now());
        int timeElapsed =  1 + parkingUpdate.getExitDate().getHour() - parkingUpdate.getEntryDate().getHour();
        parkingUpdate.setBill((double) (timeElapsed*20));

        parkingMap.replace(id, parkingUpdate);
        return parkingUpdate;
    }
}
