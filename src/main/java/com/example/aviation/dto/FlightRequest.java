package com.example.aviation.dto;

public class FlightRequest {
    private String flightNumber;
    private String status;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private Long airportId;

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public Long getAirportId() { return airportId; }
    public void setAirportId(Long airportId) { this.airportId = airportId; }
}