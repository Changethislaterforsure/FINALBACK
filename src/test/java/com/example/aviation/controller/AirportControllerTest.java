package com.example.aviation.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.aviation.model.Airport;
import com.example.aviation.repository.AirportRepository;
import com.example.aviation.service.AirportService;

@ExtendWith(MockitoExtension.class)
class AirportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AirportRepository airportRepository;

    private AirportService airportService;

    @BeforeEach
    void setup() {
        airportService = new AirportService(airportRepository);
        AirportController controller = new AirportController(airportService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    void getAllAirports_returns200AndList() throws Exception {
        Airport a = new Airport();
        a.setId(1L);
        a.setCode("YYT");
        a.setName("St. John's");
        a.setCity("St. John's");
        a.setCountry("Canada");

        when(airportRepository.findAll()).thenReturn(List.of(a));

        mockMvc.perform(get("/api/airports"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].code").value("YYT"));
    }

    @Test
    void createAirport_returns201() throws Exception {
        Airport saved = new Airport();
        saved.setId(1L);
        saved.setCode("YYT");
        saved.setName("St. John's");
        saved.setCity("St. John's");
        saved.setCountry("Canada");

        when(airportRepository.save(any(Airport.class))).thenReturn(saved);

        mockMvc.perform(post("/api/airports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"YYT\",\"name\":\"St. John's\",\"city\":\"St. John's\",\"country\":\"Canada\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("YYT"));
    }
}