package com.example.clinicaMedica.services;

import com.example.clinicaMedica.dto.DoctorDto;
import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.entities.User;
import com.example.clinicaMedica.enums.Role;
import com.example.clinicaMedica.repository.DoctorRepository;
import com.example.clinicaMedica.repository.UserRepository;
import com.example.clinicaMedica.services.exception.ObjectNotFoundException;
import com.example.clinicaMedica.services.exception.SQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

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
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Doctor not found!"));
    }

    public Doctor create(DoctorDto doctorDto) {
        try {
            User user = userRepository.findById(doctorDto.getUser().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("User not found!"));

            if (user.getRole() != Role.DOCTOR) {
                throw new ObjectNotFoundException("User with other role!");
            }

            Doctor newDoctor = Doctor.builder()
                    .user(doctorDto.getUser())
                    .crm(doctorDto.getCrm())
                    .specialty(doctorDto.getSpecialty())
                    .durationEachConsult(doctorDto.getDurationEachConsult())
                    .build();

            return repository.save(newDoctor);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new SQLIntegrityConstraintViolationException("User já setado");
        }

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
