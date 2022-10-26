package br.com.adrianomenezes.cloudparking.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parking {

    @Id
    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;

    public Parking(String id, String license, String state, String model, String color) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }
}
