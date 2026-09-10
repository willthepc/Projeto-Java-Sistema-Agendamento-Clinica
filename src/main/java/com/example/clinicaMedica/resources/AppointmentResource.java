package com.example.clinicaMedica.resources;

import com.example.clinicaMedica.dto.AppointmentDto;
import com.example.clinicaMedica.entities.Appointment;
import com.example.clinicaMedica.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
@RestController(value = "/appointment")
public class AppointmentResource {

    @Autowired
    public AppointmentService service;

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody AppointmentDto appointmentDto, @PathVariable LocalDate date) {
        Appointment appointment = service.create(appointmentDto, date);

        return ResponseEntity.ok().body(appointment);
    }
}
