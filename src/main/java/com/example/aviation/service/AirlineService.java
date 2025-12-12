package com.example.aviation.service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airline;
import com.example.aviation.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new NotFoundException("Airline not found with id " + id));
    }

    public Airline getAirlineByCode(String code) {
        return airlineRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Airline not found with code " + code));
    }

    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline updateAirline(Long id, Airline updated) {
        Airline existing = airlineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airline not found with id " + id));
        existing.setCode(updated.getCode());
        existing.setName(updated.getName());
        return airlineRepository.save(existing);
    }

    public void deleteAirline(Long id) {
        if (!airlineRepository.existsById(id)) {
            throw new NotFoundException("Airline not found with id " + id);
        }
        airlineRepository.deleteById(id);
    }
}
