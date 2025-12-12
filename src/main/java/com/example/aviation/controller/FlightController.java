package com.example.aviation.controller;

import com.example.aviation.dto.FlightRequestDto;
import com.example.aviation.model.Flight;
import com.example.aviation.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<List<Flight>> getFlightsByAirportAndType(
            @PathVariable Long airportId,
            @RequestParam String type) {
        return ResponseEntity.ok(flightService.getFlightsByAirportAndType(airportId, type));
    }

    @GetMapping("/airport/{airportId}/range")
    public ResponseEntity<List<Flight>> getFlightsByAirportAndTimeRange(
            @PathVariable Long airportId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(flightService.getFlightsByAirportAndTimeRange(airportId, start, end));
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightRequestDto dto) {
        Flight created = flightService.createFlight(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody FlightRequestDto dto) {
        Flight updated = flightService.updateFlight(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}