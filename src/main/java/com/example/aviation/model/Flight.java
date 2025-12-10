package com.example.aviation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String flightNumber; 

    @Column(nullable = false, length = 16)
    private String type; 

    @Column(nullable = false)
    private LocalDateTime scheduledTime;

    @Column(nullable = false, length = 32)
    private String status; 

    // Relationships
    @ManyToOne(optional = false)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "gate_id")
    private Gate gate;
}
