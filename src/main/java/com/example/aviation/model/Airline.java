package com.example.aviation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airlines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 8)
    private String code; 

    @Column(nullable = false)
    private String name;
}
