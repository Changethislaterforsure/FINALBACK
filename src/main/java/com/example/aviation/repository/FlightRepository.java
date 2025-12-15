package com.example.aviation.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aviation.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByAirportId(Long airportId);

    List<Flight> findByAirportIdAndScheduledTimeBetween(
            Long airportId,
            LocalDateTime start,
            LocalDateTime end
    );
}