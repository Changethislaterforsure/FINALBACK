package com.example.aviation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 8)
    private String code; 

    @Column(nullable = false)
    private String name;

    private String city;

    private String country;
}
