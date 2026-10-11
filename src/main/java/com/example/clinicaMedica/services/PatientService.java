package com.example.clinicaMedica.services;

import com.example.clinicaMedica.dto.PatientDto;
import com.example.clinicaMedica.entities.Patient;
import com.example.clinicaMedica.entities.User;
import com.example.clinicaMedica.enums.Role;
import com.example.clinicaMedica.repository.PatientRepository;
import com.example.clinicaMedica.repository.UserRepository;
import com.example.clinicaMedica.services.exception.SQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    public PatientRepository repository;

    @Autowired
    public UserRepository userRepository;

    public List<Patient> findAll() {
        return repository.findAll();
    }

    public Patient findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Patient not Found"));
    }

    public Patient create(PatientDto patientDto) {
        try {
            User user = userRepository.findById(patientDto.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Not found"));

            if (user.getRole() != Role.ROLE_PATIENT) {
                throw new RuntimeException("Role wrong!");
            }

            Patient patient = Patient.builder()
                    .user(user)
                    .birthDate(patientDto.getBirthDate())
                    .phone(patientDto.getPhone())
                    .build();

            return repository.save(patient);
        }
         catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new SQLIntegrityConstraintViolationException("User já setado");
        }
    }

    public Patient update(Long id, PatientDto patientDto) {
        Patient patient = repository.getReferenceById(id);
        patient.setUser(patientDto.getUser());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setPhone(patientDto.getPhone());

        return repository.save(patient);
    }

    public void delete(Long id) {
        Patient patient = repository.getReferenceById(id);
        repository.delete(patient);
    }
}
