package com.example.clinicaMedica.dto;

import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.entities.Patient;
import com.example.clinicaMedica.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto {
    private Long id;
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime dateTime;
    private Integer durationMinutes;
    private Status status;
}
