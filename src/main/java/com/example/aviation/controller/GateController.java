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

import com.example.aviation.dto.GateRequest;
import com.example.aviation.model.Gate;
import com.example.aviation.service.GateService;

@RestController
@RequestMapping("/api/gates")
public class GateController {

    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @GetMapping
    public ResponseEntity<List<Gate>> getAll() {
        return ResponseEntity.ok(gateService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gate> getById(@PathVariable Long id) {
        return ResponseEntity.ok(gateService.getById(id));
    }

    @GetMapping("/airport/{airportId}")
    public ResponseEntity<List<Gate>> getByAirport(@PathVariable Long airportId) {
        return ResponseEntity.ok(gateService.getByAirportId(airportId));
    }

    @PostMapping
    public ResponseEntity<Gate> create(@RequestBody GateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gateService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gate> update(@PathVariable Long id, @RequestBody GateRequest req) {
        return ResponseEntity.ok(gateService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}