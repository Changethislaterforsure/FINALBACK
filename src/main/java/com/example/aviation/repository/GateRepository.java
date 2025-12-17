package com.example.aviation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aviation.model.Gate;

public interface GateRepository extends JpaRepository<Gate, Long> {
    List<Gate> findByAirportId(Long airportId);
}