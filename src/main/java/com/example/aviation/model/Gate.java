package com.example.aviation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g. A12, B3

    @ManyToOne(optional = false)
    @JoinColumn(name = "airport_id")
    private Airport airport;
}
