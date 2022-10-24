package br.com.adrianomenezes.cloudparking.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
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
