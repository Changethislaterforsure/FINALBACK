package com.example.aviation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aviation.dto.GateRequest;
import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airport;
import com.example.aviation.model.Gate;
import com.example.aviation.repository.AirportRepository;
import com.example.aviation.repository.GateRepository;

@Service
public class GateService {

    private final GateRepository gateRepository;
    private final AirportRepository airportRepository;

    public GateService(GateRepository gateRepository, AirportRepository airportRepository) {
        this.gateRepository = gateRepository;
        this.airportRepository = airportRepository;
    }

    public List<Gate> getAll() {
        return gateRepository.findAll();
    }

    public Gate getById(Long id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gate not found: " + id));
    }

    public List<Gate> getByAirportId(Long airportId) {
        return gateRepository.findByAirportId(airportId);
    }

    public Gate create(GateRequest req) {
        Airport airport = airportRepository.findById(req.getAirportId())
                .orElseThrow(() -> new NotFoundException("Airport not found: " + req.getAirportId()));

        Gate gate = new Gate();
        gate.setCode(req.getCode());
        gate.setTerminal(req.getTerminal());
        gate.setAirport(airport);
        return gateRepository.save(gate);
    }

    public Gate update(Long id, GateRequest req) {
        Gate existing = getById(id);

        if (req.getAirportId() != null) {
            Airport airport = airportRepository.findById(req.getAirportId())
                    .orElseThrow(() -> new NotFoundException("Airport not found: " + req.getAirportId()));
            existing.setAirport(airport);
        }

        if (req.getCode() != null) existing.setCode(req.getCode());
        if (req.getTerminal() != null) existing.setTerminal(req.getTerminal());

        return gateRepository.save(existing);
    }

    public void delete(Long id) {
        Gate existing = getById(id);
        gateRepository.delete(existing);
    }
}