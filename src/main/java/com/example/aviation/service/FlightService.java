package com.example.aviation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airport;
import com.example.aviation.model.Flight;
import com.example.aviation.repository.AirportRepository;
import com.example.aviation.repository.FlightRepository;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found with id: " + id));
    }

    public List<Flight> getFlightsByAirportFiltered(Long airportId, String status, LocalDateTime start, LocalDateTime end) {
        boolean hasStatus = status != null && !status.isBlank();
        boolean hasRange = start != null && end != null;

        if (hasStatus && hasRange) {
            return flightRepository.findByAirport_IdAndStatusIgnoreCaseAndScheduledTimeBetween(airportId, status, start, end);
        }
        if (hasRange) {
            return flightRepository.findByAirport_IdAndScheduledTimeBetween(airportId, start, end);
        }
        if (hasStatus) {
            return flightRepository.findByAirport_IdAndStatusIgnoreCase(airportId, status);
        }
        return flightRepository.findByAirport_Id(airportId);
    }

    public Flight createFlight(Long airportId, Flight flight) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new NotFoundException("Airport not found with id: " + airportId));

        
        flight.setAirport(airport);

        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight updated) {
        Flight existing = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found with id: " + id));

        existing.setFlightNumber(updated.getFlightNumber());
        existing.setType(updated.getType());
        existing.setScheduledTime(updated.getScheduledTime());
        existing.setStatus(updated.getStatus());
        existing.setAirline(updated.getAirline());
        existing.setAircraft(updated.getAircraft());
        existing.setGate(updated.getGate());

        return flightRepository.save(existing);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new NotFoundException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }
}