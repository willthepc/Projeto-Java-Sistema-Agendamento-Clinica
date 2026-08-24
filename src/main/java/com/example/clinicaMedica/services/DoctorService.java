package com.example.clinicaMedica.services;

import com.example.clinicaMedica.dto.DoctorDto;
import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.entities.User;
import com.example.clinicaMedica.enums.Role;
import com.example.clinicaMedica.repository.DoctorRepository;
import com.example.clinicaMedica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    public DoctorRepository repository;

    @Autowired
    public UserRepository userRepository;

    public List<Doctor> findAll() {
        return repository.findAll();
    }

    public Doctor findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Doctor create(DoctorDto doctorDto) {
        User user = userRepository.findById(doctorDto.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (user.getRole() != Role.DOCTOR) {
            throw new RuntimeException("Role wrong!");
        }

        Doctor newDoctor = Doctor.builder()
                .user(doctorDto.getUser())
                .crm(doctorDto.getCrm())
                .specialty(doctorDto.getSpecialty())
                .durationEachConsult(doctorDto.getDurationEachConsult())
                .build();

        return repository.save(newDoctor);
    }

    public Doctor update(Long id, DoctorDto doctorDto) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.setUser(doctorDto.getUser());
        doctor.setCrm(doctorDto.getCrm());
        doctor.setSpecialty(doctorDto.getSpecialty());
        doctor.setDurationEachConsult(doctorDto.getDurationEachConsult());

        return repository.save(doctor);
    }

    public void delete(Long id) {
        Doctor doctor = repository.getReferenceById(id);
        repository.delete(doctor);
    }
}
