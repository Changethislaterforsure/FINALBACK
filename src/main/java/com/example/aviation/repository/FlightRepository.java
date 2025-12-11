package com.example.aviation.repository;

import com.example.aviation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByAirportIdAndTypeOrderByScheduledTimeAsc(Long airportId, String type);
    List<Flight> findByAirportIdAndScheduledTimeBetween(Long airportId, LocalDateTime start, LocalDateTime end);
}
