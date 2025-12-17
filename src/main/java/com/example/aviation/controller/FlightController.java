package com.example.aviation.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aviation.model.Flight;
import com.example.aviation.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/airport/{airportId}")
    public ResponseEntity<List<Flight>> getFlightsByAirport(
            @PathVariable Long airportId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        LocalDateTime startDt = (start == null || start.isBlank()) ? null : LocalDateTime.parse(start);
        LocalDateTime endDt = (end == null || end.isBlank()) ? null : LocalDateTime.parse(end);

        return ResponseEntity.ok(
                flightService.getFlightsByAirportFiltered(airportId, status, startDt, endDt)
        );
    }

    @PostMapping("/airport/{airportId}")
    public ResponseEntity<Flight> createFlight(
            @PathVariable Long airportId,
            @RequestBody Flight flight
    ) {
        return ResponseEntity.status(201).body(flightService.createFlight(airportId, flight));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(
            @PathVariable Long id,
            @RequestBody Flight updated
    ) {
        return ResponseEntity.ok(flightService.updateFlight(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}