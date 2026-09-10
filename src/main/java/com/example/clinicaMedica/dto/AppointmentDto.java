package com.example.clinicaMedica.dto;

import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.entities.Patient;
import com.example.clinicaMedica.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto {
    private Long id;
    private Doctor doctor;
    private Patient patient;
    private LocalTime dateTime;
    private Integer durationMinutes;
    private Status status;
}
