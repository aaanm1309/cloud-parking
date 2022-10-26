package br.com.adrianomenezes.cloudparking.repository;

import br.com.adrianomenezes.cloudparking.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {
}
