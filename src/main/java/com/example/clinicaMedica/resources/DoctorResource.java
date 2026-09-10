package com.example.clinicaMedica.resources;

import com.example.clinicaMedica.dto.DoctorDto;
import com.example.clinicaMedica.entities.Availability;
import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorResource {
    @Autowired
    private DoctorService service;

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll() {
        List<Doctor> doctorList = service.findAll();
        return ResponseEntity.ok().body(doctorList);
    }

   @GetMapping(value = "/{id}")
    public ResponseEntity<Doctor> findById(@PathVariable Long id) {
        Doctor doctor = service.findById(id);
        return ResponseEntity.ok().body(doctor);
   }

   @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody DoctorDto doctorDto) {
       Doctor doctor = service.create(doctorDto);

       URI uri = ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(doctor.getId())
               .toUri();

       return ResponseEntity.created(uri).body(doctor);
   }

   @PutMapping(value = "/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody DoctorDto doctorDto) {
        Doctor doctor = service.update(id, doctorDto);
        return ResponseEntity.ok().body(doctor);
   }

   @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
   }


}
