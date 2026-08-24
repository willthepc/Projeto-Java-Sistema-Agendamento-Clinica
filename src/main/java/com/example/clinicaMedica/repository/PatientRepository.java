package com.example.clinicaMedica.repository;

import com.example.clinicaMedica.entities.Patient;
import com.example.clinicaMedica.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Long id(Long id);
}
