package com.example.clinicaMedica.dto;

import com.example.clinicaMedica.entities.User;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDto {
    private Long id;
    private User user;
    @Past(message = "Data precisa ser no passado")
    private LocalDate birthDate;
    private String phone;
}
