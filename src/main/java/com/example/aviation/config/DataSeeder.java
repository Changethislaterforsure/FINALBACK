package com.example.aviation.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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

@Configuration
@Profile("dev")
public class DataSeeder {
    @Bean
    public CommandLineRunner seedData(
            AirportRepository airportRepository,
            AirlineRepository airlineRepository,
            AircraftRepository aircraftRepository,
            GateRepository gateRepository,
            FlightRepository flightRepository
    ) {
        return args -> {
            if (airportRepository.count() > 0) return;

            Airport yyt = new Airport();
            yyt.setCode("YYT");
            yyt.setName("St. John's International Airport");
            yyt.setCity("St. John's");
            yyt.setCountry("Canada");
            yyt = airportRepository.save(yyt);

            Airline ac = new Airline();
            ac.setCode("AC");
            ac.setName("Air Canada");
            ac = airlineRepository.save(ac);

            Aircraft b737 = new Aircraft();
            b737.setModel("Boeing 737");
            b737.setCapacity(160);
            b737 = aircraftRepository.save(b737);

            Gate a1 = new Gate();
            a1.setName("A1");
            a1.setAirport(yyt);
            a1 = gateRepository.save(a1);

            Flight f1 = new Flight();
            f1.setFlightNumber("AC123");
            f1.setType("ARRIVAL");
            f1.setScheduledTime(LocalDateTime.now().plusHours(2));
            f1.setStatus("ON_TIME");
            f1.setAirport(yyt);
            f1.setAirline(ac);
            f1.setAircraft(b737);
            f1.setGate(a1);
            flightRepository.save(f1);
        };
    }
}