package com.example.clinicaMedica.entities;

import com.example.clinicaMedica.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    private LocalTime dateTime;

    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    private Status status;

}
