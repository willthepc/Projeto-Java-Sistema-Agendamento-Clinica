package com.example.clinicaMedica.resources;

import com.example.clinicaMedica.dto.SpecialtyDto;
import com.example.clinicaMedica.entities.Specialty;
import com.example.clinicaMedica.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/specialties")
public class SpecialtyResource {
    @Autowired
    private SpecialtyService service;

    @GetMapping
    public ResponseEntity<List<Specialty>> findAll() {
        List<Specialty> specialtyList = service.findAll();
        return ResponseEntity.ok().body(specialtyList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Specialty> findById(@PathVariable Long id) {
        Specialty specialty = service.findById(id);
        return ResponseEntity.ok().body(specialty);
    }

    @PostMapping
    public ResponseEntity<Specialty> create(@RequestBody SpecialtyDto specialtyDto) {
        Specialty specialty = service.create(specialtyDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(specialty.getId())
                .toUri();

        return ResponseEntity.created(uri).body(specialty);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Specialty> update(@PathVariable Long id, @RequestBody SpecialtyDto specialtyDto) {
        Specialty specialty = service.update(id, specialtyDto);
        return ResponseEntity.ok().body(specialty);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
