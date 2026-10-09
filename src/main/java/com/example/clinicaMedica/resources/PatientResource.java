package com.example.clinicaMedica.resources;

import com.example.clinicaMedica.dto.PatientDto;
import com.example.clinicaMedica.entities.Patient;
import com.example.clinicaMedica.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/patients")
public class PatientResource {
    @Autowired
    private PatientService service;

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> patientsList = service.findAll();
        return ResponseEntity.ok().body(patientsList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {
        Patient patient = service.findById(id);
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> create(@Valid @RequestBody PatientDto patientDto) {
        Patient patient = service.create(patientDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patient.getId())
                .toUri();

        return ResponseEntity.created(uri).body(patient);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody PatientDto patientDto) {
        Patient patient = service.update(id, patientDto);
        return ResponseEntity.ok().body(patient);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
