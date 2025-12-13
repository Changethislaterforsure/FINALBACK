package com.example.aviation.service;

import com.example.aviation.exception.NotFoundException;
import com.example.aviation.model.Airport;
import com.example.aviation.model.Gate;
import com.example.aviation.repository.AirportRepository;
import com.example.aviation.repository.GateRepository;
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
class GateServiceTest {

    @Mock
    private GateRepository gateRepository;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private GateService gateService;

    @Test
    void getAllGates_returnsList() {
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCode("YYT");
        airport.setName("St. John's");

        Gate g1 = new Gate();
        g1.setId(10L);
        g1.setName("A1");
        g1.setAirport(airport);

        Gate g2 = new Gate();
        g2.setId(11L);
        g2.setName("A2");
        g2.setAirport(airport);

        when(gateRepository.findAll()).thenReturn(List.of(g1, g2));

        List<Gate> result = gateService.getAllGates();

        assertEquals(2, result.size());
        verify(gateRepository, times(1)).findAll();
    }

    @Test
    void getGateById_whenFound_returnsGate() {
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCode("YYT");
        airport.setName("St. John's");

        Gate gate = new Gate();
        gate.setId(10L);
        gate.setName("A1");
        gate.setAirport(airport);

        when(gateRepository.findById(10L)).thenReturn(Optional.of(gate));

        Gate result = gateService.getGateById(10L);

        assertEquals("A1", result.getName());
        verify(gateRepository, times(1)).findById(10L);
    }

    @Test
    void getGateById_whenMissing_throwsNotFound() {
        when(gateRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> gateService.getGateById(999L));
        verify(gateRepository, times(1)).findById(999L);
    }

    @Test
    void getGatesByAirport_returnsList() {
        when(gateRepository.findByAirportId(1L)).thenReturn(List.of(new Gate(), new Gate()));

        List<Gate> result = gateService.getGatesByAirport(1L);

        assertEquals(2, result.size());
        verify(gateRepository, times(1)).findByAirportId(1L);
    }

    @Test
    void createGate_setsAirportAndSaves() {
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCode("YYT");
        airport.setName("St. John's");

        Gate input = new Gate();
        input.setName("A1");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));
        when(gateRepository.save(any(Gate.class))).thenAnswer(inv -> inv.getArgument(0));

        Gate created = gateService.createGate(1L, input);

        assertNotNull(created.getAirport());
        assertEquals(1L, created.getAirport().getId());
        assertEquals("A1", created.getName());

        ArgumentCaptor<Gate> captor = ArgumentCaptor.forClass(Gate.class);
        verify(gateRepository).save(captor.capture());
        assertEquals(1L, captor.getValue().getAirport().getId());
        assertEquals("A1", captor.getValue().getName());
    }

    @Test
    void createGate_whenAirportMissing_throwsNotFound() {
        Gate input = new Gate();
        input.setName("A1");

        when(airportRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> gateService.createGate(1L, input));
        verify(gateRepository, never()).save(any(Gate.class));
    }

    @Test
    void updateGate_updatesName_andOptionallyAirport() {
        Airport oldAirport = new Airport();
        oldAirport.setId(1L);

        Airport newAirport = new Airport();
        newAirport.setId(2L);

        Gate existing = new Gate();
        existing.setId(10L);
        existing.setName("OLD");
        existing.setAirport(oldAirport);

        Gate updated = new Gate();
        updated.setName("B2");

        Airport updatedAirport = new Airport();
        updatedAirport.setId(2L);
        updated.setAirport(updatedAirport);

        when(gateRepository.findById(10L)).thenReturn(Optional.of(existing));
        when(airportRepository.findById(2L)).thenReturn(Optional.of(newAirport));
        when(gateRepository.save(any(Gate.class))).thenAnswer(inv -> inv.getArgument(0));

        Gate result = gateService.updateGate(10L, updated);

        assertEquals("B2", result.getName());
        assertEquals(2L, result.getAirport().getId());
        verify(gateRepository, times(1)).save(existing);
    }

    @Test
    void deleteGate_whenExists_deletes() {
        when(gateRepository.existsById(10L)).thenReturn(true);

        gateService.deleteGate(10L);

        verify(gateRepository, times(1)).existsById(10L);
        verify(gateRepository, times(1)).deleteById(10L);
    }

    @Test
    void deleteGate_whenMissing_throwsNotFound() {
        when(gateRepository.existsById(10L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> gateService.deleteGate(10L));
        verify(gateRepository, times(1)).existsById(10L);
        verify(gateRepository, never()).deleteById(anyLong());
    }
}