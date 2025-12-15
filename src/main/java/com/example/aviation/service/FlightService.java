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
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final AircraftRepository aircraftRepository;
    private final GateRepository gateRepository;

    public FlightService(
            FlightRepository flightRepository,
            AirportRepository airportRepository,
            AirlineRepository airlineRepository,
            AircraftRepository aircraftRepository,
            GateRepository gateRepository
    ) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.aircraftRepository = aircraftRepository;
        this.gateRepository = gateRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getFlightsByAirport(Long airportId) {
        if (!airportRepository.existsById(airportId)) {
            throw new NotFoundException("Airport not found with id: " + airportId);
        }
        return flightRepository.findByAirportId(airportId);
    }

    public List<Flight> getFlightsByAirportInRange(Long airportId, LocalDateTime start, LocalDateTime end) {
        if (!airportRepository.existsById(airportId)) {
            throw new NotFoundException("Airport not found with id: " + airportId);
        }
        return flightRepository.findByAirportIdAndScheduledTimeBetween(airportId, start, end);
    }

    public Flight createFlight(FlightRequestDto dto) {
        Airport airport = airportRepository.findById(dto.getAirportId())
                .orElseThrow(() -> new NotFoundException("Airport not found with id: " + dto.getAirportId()));

        Airline airline = airlineRepository.findById(dto.getAirlineId())
                .orElseThrow(() -> new NotFoundException("Airline not found with id: " + dto.getAirlineId()));

        Aircraft aircraft = aircraftRepository.findById(dto.getAircraftId())
                .orElseThrow(() -> new NotFoundException("Aircraft not found with id: " + dto.getAircraftId()));

        Gate gate = gateRepository.findById(dto.getGateId())
                .orElseThrow(() -> new NotFoundException("Gate not found with id: " + dto.getGateId()));

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
}