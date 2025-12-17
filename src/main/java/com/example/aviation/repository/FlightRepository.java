package com.example.aviation.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aviation.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByAirport_Id(Long airportId);

    List<Flight> findByAirport_IdAndStatusIgnoreCase(Long airportId, String status);

    List<Flight> findByAirport_IdAndScheduledTimeBetween(Long airportId, LocalDateTime start, LocalDateTime end);

    List<Flight> findByAirport_IdAndStatusIgnoreCaseAndScheduledTimeBetween(
            Long airportId,
            String status,
            LocalDateTime start,
            LocalDateTime end
    );
}