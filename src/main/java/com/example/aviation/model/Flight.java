package com.example.aviation.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String flightNumber;

    @Column(nullable = false, length = 16)
    private String type;

    @Column(nullable = false)
    private LocalDateTime scheduledTime;

    @Column(nullable = false, length = 32)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "gate_id")
    private Gate gate;

    public Flight() {
    }

    public Flight(Long id, String flightNumber, String type, LocalDateTime scheduledTime, String status,
                  Airport airport, Airline airline, Aircraft aircraft, Gate gate) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.type = type;
        this.scheduledTime = scheduledTime;
        this.status = status;
        this.airport = airport;
        this.airline = airline;
        this.aircraft = aircraft;
        this.gate = gate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}