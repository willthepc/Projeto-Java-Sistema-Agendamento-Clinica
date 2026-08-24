package com.example.clinicaMedica.services;

import com.example.clinicaMedica.dto.SpecialtyDto;
import com.example.clinicaMedica.entities.Specialty;
import com.example.clinicaMedica.repository.SpecialtyRepository;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {
    @Autowired
    public SpecialtyRepository repository;

    public List<Specialty> findAll() {
        return repository.findAll();
    }

    public Specialty findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Specialty create(SpecialtyDto specialtyDto) {
        Specialty specialty = Specialty.builder()
                .id(specialtyDto.getId())
                .name(specialtyDto.getName())
                .build();

        return repository.save(specialty);
    }

    public Specialty update(Long id, SpecialtyDto specialtyDto) {
        Specialty specialty = repository.getReferenceById(id);

        specialty.setId(specialty.getId());
        specialty.setName(specialtyDto.getName());

        return repository.save(specialty);
    }

    public void delete(Long id) {
        Specialty specialty = repository.getReferenceById(id);
        repository.delete(specialty);
    }

}
