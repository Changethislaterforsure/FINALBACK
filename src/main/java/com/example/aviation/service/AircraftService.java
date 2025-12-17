package com.example.aviation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Aircraft;
import com.example.aviation.repository.AircraftRepository;

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
                .orElseThrow(() -> new NotFoundException("Aircraft not found with id: " + id));
    }

    public Aircraft getAircraftByRegistration(String registration) {
        return aircraftRepository.findByRegistrationIgnoreCase(registration)
                .orElseThrow(() -> new NotFoundException("Aircraft not found with registration: " + registration));
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        aircraft.setId(null);
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft updated) {
        Aircraft existing = getAircraftById(id);
        existing.setModel(updated.getModel());
        existing.setManufacturer(updated.getManufacturer());
        existing.setCapacity(updated.getCapacity());
        existing.setRegistration(updated.getRegistration());
        return aircraftRepository.save(existing);
    }

    public void deleteAircraft(Long id) {
        Aircraft existing = getAircraftById(id);
        aircraftRepository.delete(existing);
    }
}
