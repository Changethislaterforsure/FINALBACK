package com.example.aviation.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aviation.dto.FlightRequestDto;
import com.example.aviation.model.Flight;
import com.example.aviation.service.FlightService;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/airport/{airportId}")
    public List<Flight> getFlightsByAirport(@PathVariable Long airportId) {
        return flightService.getFlightsByAirport(airportId);
    }

    @GetMapping("/airport/{airportId}/range")
    public List<Flight> getFlightsByAirportInRange(
            @PathVariable Long airportId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        return flightService.getFlightsByAirportInRange(airportId, start, end);
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightRequestDto dto) {
        Flight created = flightService.createFlight(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}