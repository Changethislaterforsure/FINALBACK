package com.example.aviation.service;

import com.example.aviation.dto.FlightRequestDto;
import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Aircraft;
import com.example.aviation.model.Airline;
import com.example.aviation.model.Airport;
import com.example.aviation.model.Flight;
import com.example.aviation.model.Gate;
import com.example.aviation.repository.AircraftRepository;
import com.example.aviation.repository.AirlineRepository;
import com.example.aviation.repository.AirportRepository;
import com.example.aviation.repository.FlightRepository;
import com.example.aviation.repository.GateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final AircraftRepository aircraftRepository;
    private final GateRepository gateRepository;

    public FlightService(FlightRepository flightRepository,
                         AirportRepository airportRepository,
                         AirlineRepository airlineRepository,
                         AircraftRepository aircraftRepository,
                         GateRepository gateRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.aircraftRepository = aircraftRepository;
        this.gateRepository = gateRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found with id " + id));
    }

    public List<Flight> getFlightsByAirportAndType(Long airportId, String type) {
        return flightRepository.findByAirportIdAndTypeOrderByScheduledTimeAsc(airportId, type);
    }

    public List<Flight> getFlightsByAirportAndTimeRange(Long airportId, LocalDateTime start, LocalDateTime end) {
        return flightRepository.findByAirportIdAndScheduledTimeBetween(airportId, start, end);
    }

    public Flight createFlight(FlightRequestDto dto) {
        Airport airport = airportRepository.findById(dto.getAirportId())
                .orElseThrow(() -> new NotFoundException("Airport not found with id " + dto.getAirportId()));
        Airline airline = airlineRepository.findById(dto.getAirlineId())
                .orElseThrow(() -> new NotFoundException("Airline not found with id " + dto.getAirlineId()));
        Aircraft aircraft = aircraftRepository.findById(dto.getAircraftId())
                .orElseThrow(() -> new NotFoundException("Aircraft not found with id " + dto.getAircraftId()));

        Gate gate = null;
        if (dto.getGateId() != null) {
            gate = gateRepository.findById(dto.getGateId())
                    .orElseThrow(() -> new NotFoundException("Gate not found with id " + dto.getGateId()));
        }

        Flight flight = new Flight();
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setType(dto.getType());
        flight.setScheduledTime(dto.getScheduledTime());
        flight.setStatus(dto.getStatus());
        flight.setAirport(airport);
        flight.setAirline(airline);
        flight.setAircraft(aircraft);
        flight.setGate(gate);

        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, FlightRequestDto dto) {
        Flight existing = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found with id " + id));

        Airport airport = airportRepository.findById(dto.getAirportId())
                .orElseThrow(() -> new NotFoundException("Airport not found with id " + dto.getAirportId()));
        Airline airline = airlineRepository.findById(dto.getAirlineId())
                .orElseThrow(() -> new NotFoundException("Airline not found with id " + dto.getAirlineId()));
        Aircraft aircraft = aircraftRepository.findById(dto.getAircraftId())
                .orElseThrow(() -> new NotFoundException("Aircraft not found with id " + dto.getAircraftId()));

        Gate gate = null;
        if (dto.getGateId() != null) {
            gate = gateRepository.findById(dto.getGateId())
                    .orElseThrow(() -> new NotFoundException("Gate not found with id " + dto.getGateId()));
        }

        existing.setFlightNumber(dto.getFlightNumber());
        existing.setType(dto.getType());
        existing.setScheduledTime(dto.getScheduledTime());
        existing.setStatus(dto.getStatus());
        existing.setAirport(airport);
        existing.setAirline(airline);
        existing.setAircraft(aircraft);
        existing.setGate(gate);

        return flightRepository.save(existing);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new NotFoundException("Flight not found with id " + id);
        }
        flightRepository.deleteById(id);
    }
}