package com.example.clinicaMedica.dto;

import com.example.clinicaMedica.entities.Specialty;
import com.example.clinicaMedica.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorDto {
    private Long id;
    private User user;
    private String crm;
    private Specialty specialty;
    private Integer durationEachConsult;
}
