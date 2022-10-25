package br.com.adrianomenezes.cloudparking.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ParkingCreateDTO {

        private String license;
        private String state;
        private String model;
        private String color;

}
