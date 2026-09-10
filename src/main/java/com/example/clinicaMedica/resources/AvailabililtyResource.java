package com.example.clinicaMedica.resources;

import com.example.clinicaMedica.dto.AvailabilityDto;
import com.example.clinicaMedica.entities.Availability;
import com.example.clinicaMedica.services.AvailabilityService;
import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/doctors/{doctorId}/availability")
@Validated
public class AvailabililtyResource {

    @Autowired
    public AvailabilityService service;

    @GetMapping
    public List<Availability> findAvailability(@PathVariable Long doctorId) {
        return service.findDoctorById(doctorId);
    }

    @PostMapping
    public ResponseEntity<Availability> create(@PathVariable Long doctorId,
                                               @RequestBody AvailabilityDto availabilityDto) {
        Availability availability = service.create(doctorId, availabilityDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(availability.getId())
                .toUri();

        return ResponseEntity.created(uri).body(availability);
    }

    @PutMapping
    public ResponseEntity<Availability> update(@PathVariable Long id,
                                               @RequestBody AvailabilityDto availabilityDto) {
        Availability availability = service.update(id, availabilityDto);
        return ResponseEntity.ok().body(availability);
    }


    @DeleteMapping
    public ResponseEntity<Void> delete(
            @PathVariable Long doctorId,
            @PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/available-slots")
    public ResponseEntity<List<LocalTime>> getAvailableSlots(@PathVariable Long doctorId,
                                                             @RequestParam(value = "date")
                                                             @FutureOrPresent(message = "A data não pode estar no passado") LocalDate date) {

        List<LocalTime> horarios = service.findAvailableSlots(doctorId, date);
        return ResponseEntity.ok().body(horarios);
    }
}
