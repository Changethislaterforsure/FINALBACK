package com.example.aviation.service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airline;
import com.example.aviation.repository.AirlineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineService airlineService;

    @Test
    void getAllAirlines_returnsList() {
        Airline a1 = new Airline();
        a1.setId(1L);
        a1.setCode("AC");
        a1.setName("Air Canada");

        Airline a2 = new Airline();
        a2.setId(2L);
        a2.setCode("WS");
        a2.setName("WestJet");

        when(airlineRepository.findAll()).thenReturn(List.of(a1, a2));

        List<Airline> result = airlineService.getAllAirlines();

        assertEquals(2, result.size());
        verify(airlineRepository, times(1)).findAll();
    }

    @Test
    void getAirlineByCode_whenFound_returnsAirline() {
        Airline airline = new Airline();
        airline.setId(1L);
        airline.setCode("AC");
        airline.setName("Air Canada");

        when(airlineRepository.findByCode("AC")).thenReturn(Optional.of(airline));

        Airline result = airlineService.getAirlineByCode("AC");

        assertEquals("Air Canada", result.getName());
        verify(airlineRepository, times(1)).findByCode("AC");
    }

    @Test
    void getAirlineByCode_whenMissing_throwsNotFound() {
        when(airlineRepository.findByCode("XX")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airlineService.getAirlineByCode("XX"));
        verify(airlineRepository, times(1)).findByCode("XX");
    }
}