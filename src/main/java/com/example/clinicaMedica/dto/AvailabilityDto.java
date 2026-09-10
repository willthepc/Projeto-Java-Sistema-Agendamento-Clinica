package com.example.clinicaMedica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityDto {
    private List<DayOfWeek> dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
