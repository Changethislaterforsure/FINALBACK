package com.example.aviation.service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airport;
import com.example.aviation.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airport not found with id " + id));
    }

    public Airport getAirportByCode(String code) {
        return airportRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Airport not found with code " + code));
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport updated) {
        Airport existing = airportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airport not found with id " + id));
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setCity(updated.getCity());
        existing.setCountry(updated.getCountry());
        return airportRepository.save(existing);
    }

    public void deleteAirport(Long id) {
        if (!airportRepository.existsById(id)) {
            throw new NotFoundException("Airport not found with id " + id);
        }
        airportRepository.deleteById(id);
    }
}