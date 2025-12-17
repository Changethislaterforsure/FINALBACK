package com.example.aviation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aviation.model.Airline;
import com.example.aviation.service.AirlineService;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public ResponseEntity<List<Airline>> getAllAirlines() {
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Long id) {
        return ResponseEntity.ok(airlineService.getAirlineById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Airline> getAirlineByCode(@PathVariable String code) {
        return ResponseEntity.ok(airlineService.getAirlineByCode(code));
    }

    @PostMapping
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
        return ResponseEntity.status(HttpStatus.CREATED).body(airlineService.createAirline(airline));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airline> updateAirline(@PathVariable Long id, @RequestBody Airline airline) {
        return ResponseEntity.ok(airlineService.updateAirline(id, airline));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
        return ResponseEntity.noContent().build();
    }
}