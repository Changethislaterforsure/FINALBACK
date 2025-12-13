package com.example.aviation.service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airport;
import com.example.aviation.repository.AirportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @Test
    void getAllAirports_returnsList() {
        Airport a1 = new Airport();
        a1.setId(1L);
        a1.setCode("YYT");
        a1.setName("St. John's");
        a1.setCity("St. John's");
        a1.setCountry("Canada");

        Airport a2 = new Airport();
        a2.setId(2L);
        a2.setCode("YYZ");
        a2.setName("Toronto Pearson");
        a2.setCity("Toronto");
        a2.setCountry("Canada");

        when(airportRepository.findAll()).thenReturn(List.of(a1, a2));

        List<Airport> result = airportService.getAllAirports();

        assertEquals(2, result.size());
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void getAirportById_whenFound_returnsAirport() {
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCode("YYT");
        airport.setName("St. John's");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));

        Airport result = airportService.getAirportById(1L);

        assertEquals("YYT", result.getCode());
        verify(airportRepository, times(1)).findById(1L);
    }

    @Test
    void getAirportById_whenMissing_throwsNotFound() {
        when(airportRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airportService.getAirportById(99L));
        verify(airportRepository, times(1)).findById(99L);
    }

    @Test
    void createAirport_savesAirport() {
        Airport input = new Airport();
        input.setCode("YYT");
        input.setName("St. John's");

        Airport saved = new Airport();
        saved.setId(1L);
        saved.setCode("YYT");
        saved.setName("St. John's");

        when(airportRepository.save(any(Airport.class))).thenReturn(saved);

        Airport result = airportService.createAirport(input);

        assertEquals(1L, result.getId());
        verify(airportRepository, times(1)).save(input);
    }

    @Test
    void updateAirport_updatesFields() {
        Airport existing = new Airport();
        existing.setId(1L);
        existing.setCode("OLD");
        existing.setName("Old");
        existing.setCity("Old City");
        existing.setCountry("Old Country");

        Airport updated = new Airport();
        updated.setCode("YYT");
        updated.setName("St. John's");
        updated.setCity("St. John's");
        updated.setCountry("Canada");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(airportRepository.save(any(Airport.class))).thenAnswer(inv -> inv.getArgument(0));

        Airport result = airportService.updateAirport(1L, updated);

        assertEquals("YYT", result.getCode());
        assertEquals("St. John's", result.getName());
        assertEquals("Canada", result.getCountry());

        ArgumentCaptor<Airport> captor = ArgumentCaptor.forClass(Airport.class);
        verify(airportRepository).save(captor.capture());
        assertEquals("YYT", captor.getValue().getCode());
    }

    @Test
    void deleteAirport_whenExists_deletes() {
        when(airportRepository.existsById(1L)).thenReturn(true);

        airportService.deleteAirport(1L);

        verify(airportRepository, times(1)).existsById(1L);
        verify(airportRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteAirport_whenMissing_throwsNotFound() {
        when(airportRepository.existsById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> airportService.deleteAirport(1L));
        verify(airportRepository, times(1)).existsById(1L);
        verify(airportRepository, never()).deleteById(anyLong());
    }
}