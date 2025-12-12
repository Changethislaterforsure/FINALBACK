package com.example.aviation.controller;

import com.example.aviation.model.Gate;
import com.example.aviation.service.GateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gates")
@CrossOrigin(origins = "*")
public class GateController {

    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @GetMapping
    public ResponseEntity<List<Gate>> getAllGates() {
        return ResponseEntity.ok(gateService.getAllGates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gate> getGateById(@PathVariable Long id) {
        return ResponseEntity.ok(gateService.getGateById(id));
    }

    @GetMapping("/airport/{airportId}")
    public ResponseEntity<List<Gate>> getGatesByAirport(@PathVariable Long airportId) {
        return ResponseEntity.ok(gateService.getGatesByAirport(airportId));
    }

    @PostMapping("/airport/{airportId}")
    public ResponseEntity<Gate> createGateForAirport(@PathVariable Long airportId, @RequestBody Gate gate) {
        Gate created = gateService.createGate(airportId, gate);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gate> updateGate(@PathVariable Long id, @RequestBody Gate gate) {
        Gate updated = gateService.updateGate(id, gate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGate(@PathVariable Long id) {
        gateService.deleteGate(id);
        return ResponseEntity.noContent().build();
    }
}