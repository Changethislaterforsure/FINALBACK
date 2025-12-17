package com.example.aviation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airport;
import com.example.aviation.repository.AirportRepository;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport createAirport(Airport airport) {
        airport.setId(null);
        return airportRepository.save(airport);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airport not found with id: " + id));
    }

    public Airport getAirportByCode(String code) {
        return airportRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Airport not found with code: " + code));
    }

    public Airport updateAirport(Long id, Airport updated) {
        Airport existing = getAirportById(id);
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        existing.setCity(updated.getCity());
        existing.setCountry(updated.getCountry());
        return airportRepository.save(existing);
    }

    public void deleteAirport(Long id) {
        Airport existing = getAirportById(id);
        airportRepository.delete(existing);
    }
}