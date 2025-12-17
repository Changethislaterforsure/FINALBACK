package com.example.aviation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aviation.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByCodeIgnoreCase(String code);
    boolean existsByCodeIgnoreCase(String code);
}