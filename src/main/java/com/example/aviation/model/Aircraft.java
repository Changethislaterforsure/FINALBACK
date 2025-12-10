package com.example.aviation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aircraft")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model; 

    private Integer capacity;
}
