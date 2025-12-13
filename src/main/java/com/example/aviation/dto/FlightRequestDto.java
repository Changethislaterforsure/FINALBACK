package com.example.aviation.dto;

import java.time.LocalDateTime;

public class FlightRequestDto {
    private String flightNumber;
    private String type;
    private LocalDateTime scheduledTime;
    private String status;
    private Long airportId;
    private Long airlineId;
    private Long aircraftId;
    private Long gateId;

    public FlightRequestDto() {
    }

    public FlightRequestDto(String flightNumber, String type, LocalDateTime scheduledTime, String status,
                            Long airportId, Long airlineId, Long aircraftId, Long gateId) {
        this.flightNumber = flightNumber;
        this.type = type;
        this.scheduledTime = scheduledTime;
        this.status = status;
        this.airportId = airportId;
        this.airlineId = airlineId;
        this.aircraftId = aircraftId;
        this.gateId = gateId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }
}