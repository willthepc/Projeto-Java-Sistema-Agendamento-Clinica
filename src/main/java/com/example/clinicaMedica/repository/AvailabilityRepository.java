package com.example.clinicaMedica.repository;

import com.example.clinicaMedica.entities.Availability;
import com.example.clinicaMedica.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByDoctorById(Long doctorId);
}
