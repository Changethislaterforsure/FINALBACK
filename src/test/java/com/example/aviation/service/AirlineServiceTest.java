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
        when(airlineRepository.findAll()).thenReturn(List.of(
                Airline.builder().id(1L).code("AC").name("Air Canada").build(),
                Airline.builder().id(2L).code("WS").name("WestJet").build()
        ));

        List<Airline> result = airlineService.getAllAirlines();

        assertEquals(2, result.size());
        verify(airlineRepository, times(1)).findAll();
    }

    @Test
    void getAirlineByCode_whenFound_returnsAirline() {
        Airline airline = Airline.builder().id(1L).code("AC").name("Air Canada").build();
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