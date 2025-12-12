package com.example.aviation.service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airport;
import com.example.aviation.model.Gate;
import com.example.aviation.repository.AirportRepository;
import com.example.aviation.repository.GateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {

    private final GateRepository gateRepository;
    private final AirportRepository airportRepository;

    public GateService(GateRepository gateRepository, AirportRepository airportRepository) {
        this.gateRepository = gateRepository;
        this.airportRepository = airportRepository;
    }

    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }

    public Gate getGateById(Long id) {
        return gateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gate not found with id " + id));
    }

    public List<Gate> getGatesByAirport(Long airportId) {
        return gateRepository.findByAirportId(airportId);
    }

    public Gate createGate(Long airportId, Gate gate) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new NotFoundException("Airport not found with id " + airportId));
        gate.setAirport(airport);
        return gateRepository.save(gate);
    }

    public Gate updateGate(Long id, Gate updated) {
        Gate existing = gateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gate not found with id " + id));
        existing.setName(updated.getName());
        if (updated.getAirport() != null && updated.getAirport().getId() != null) {
            Airport airport = airportRepository.findById(updated.getAirport().getId())
                    .orElseThrow(() -> new NotFoundException("Airport not found with id " + updated.getAirport().getId()));
            existing.setAirport(airport);
        }
        return gateRepository.save(existing);
    }

    public void deleteGate(Long id) {
        if (!gateRepository.existsById(id)) {
            throw new NotFoundException("Gate not found with id " + id);
        }
        gateRepository.deleteById(id);
    }
}