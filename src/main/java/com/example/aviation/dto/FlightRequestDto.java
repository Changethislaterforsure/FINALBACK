package com.example.aviation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequestDto {
    private String flightNumber;
    private String type;
    private LocalDateTime scheduledTime;
    private String status;
    private Long airportId;
    private Long airlineId;
    private Long aircraftId;
    private Long gateId;
}