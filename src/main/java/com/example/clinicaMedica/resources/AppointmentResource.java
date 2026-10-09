package com.example.clinicaMedica.resources;

import com.example.clinicaMedica.dto.AppointmentDto;
import com.example.clinicaMedica.entities.Appointment;
import com.example.clinicaMedica.enums.Status;
import com.example.clinicaMedica.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/v1/appointment")
public class AppointmentResource {

    @Autowired
    public AppointmentService service;

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody AppointmentDto appointmentDto) {
        LocalDate date = appointmentDto.getDateTime().toLocalDate();
        Appointment appointment = service.create(appointmentDto, date);
        return ResponseEntity.ok().body(appointment);
    }

    @PutMapping(value = "/cancel/{id}")
    public ResponseEntity<Appointment> updateStatusCancel(@PathVariable Long id) {
        Appointment appointment = service.updateStatusCancel(id);
        return ResponseEntity.ok().body(appointment);
    }

    @GetMapping(value = "/status/{id}")
    public ResponseEntity<Status> getStatus(@PathVariable Long id) {
        Status status = service.statusAtual(id);
        return ResponseEntity.ok().body(status);
    }
}
