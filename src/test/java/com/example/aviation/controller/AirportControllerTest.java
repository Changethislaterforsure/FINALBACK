package com.example.aviation.controller;

import com.example.aviation.model.Airport;
import com.example.aviation.service.AirportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AirportController.class)
class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    @Test
    void getAllAirports_returns200AndList() throws Exception {
        when(airportService.getAllAirports()).thenReturn(List.of(
                Airport.builder().id(1L).code("YYT").name("St. John's").city("St. John's").country("Canada").build()
        ));

        mockMvc.perform(get("/api/airports"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].code").value("YYT"));
    }

    @Test
    void createAirport_returns201() throws Exception {
        Airport created = Airport.builder().id(1L).code("YYT").name("St. John's").city("St. John's").country("Canada").build();
        when(airportService.createAirport(org.mockito.ArgumentMatchers.any(Airport.class))).thenReturn(created);

        mockMvc.perform(post("/api/airports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"YYT\",\"name\":\"St. John's\",\"city\":\"St. John's\",\"country\":\"Canada\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("YYT"));
    }
}