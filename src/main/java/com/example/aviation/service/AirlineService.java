package com.example.aviation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airline;
import com.example.aviation.repository.AirlineRepository;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirlineById(Long id) {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airline not found with id: " + id));
    }

    public Airline getAirlineByCode(String code) {
        return airlineRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Airline not found with code: " + code));
    }

    public Airline createAirline(Airline airline) {
        airline.setId(null);
        return airlineRepository.save(airline);
    }

    public Airline updateAirline(Long id, Airline updated) {
        Airline existing = getAirlineById(id);
        existing.setName(updated.getName());
        existing.setCode(updated.getCode());
        return airlineRepository.save(existing);
    }

    public void deleteAirline(Long id) {
        Airline existing = getAirlineById(id);
        airlineRepository.delete(existing);
    }
}