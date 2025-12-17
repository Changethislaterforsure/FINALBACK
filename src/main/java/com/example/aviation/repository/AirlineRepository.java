package com.example.aviation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aviation.model.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByCodeIgnoreCase(String code);
}