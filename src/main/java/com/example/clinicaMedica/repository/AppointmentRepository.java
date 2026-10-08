package com.example.clinicaMedica.repository;

import com.example.clinicaMedica.entities.Appointment;
import com.example.clinicaMedica.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
}
