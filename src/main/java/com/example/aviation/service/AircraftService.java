package com.example.aviation.service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Aircraft;
import com.example.aviation.repository.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aircraft not found with id " + id));
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft updated) {
        Aircraft existing = aircraftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aircraft not found with id " + id));
        existing.setModel(updated.getModel());
        existing.setCapacity(updated.getCapacity());
        return aircraftRepository.save(existing);
    }

    public void deleteAircraft(Long id) {
        if (!aircraftRepository.existsById(id)) {
            throw new NotFoundException("Aircraft not found with id " + id);
        }
        aircraftRepository.deleteById(id);
    }
}
