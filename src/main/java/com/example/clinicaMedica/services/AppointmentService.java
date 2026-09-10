package com.example.clinicaMedica.services;

import com.example.clinicaMedica.dto.AppointmentDto;
import com.example.clinicaMedica.entities.Appointment;
import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.entities.Patient;
import com.example.clinicaMedica.enums.Status;
import com.example.clinicaMedica.repository.AppointmentRepository;
import com.example.clinicaMedica.repository.DoctorRepository;
import com.example.clinicaMedica.repository.PatientRepository;
import com.example.clinicaMedica.resources.PatientResource;
import com.example.clinicaMedica.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    public AppointmentRepository repository;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Appointment create(AppointmentDto appointmentDto, LocalDate date) {
        List<LocalTime> localTime = availabilityService.findAvailableSlots(appointmentDto.getDoctor().getId(), date);

        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctor().getId())
                .orElseThrow(() -> new ObjectNotFoundException("Doctor not Found!"));

        Patient patient = patientRepository.findById(appointmentDto.getPatient().getId())
                .orElseThrow(() -> new ObjectNotFoundException("Patient not Found!"));

        for (LocalTime horarioDesejado : localTime) {
            if (horarioDesejado.equals(appointmentDto.getDateTime())) {
                Appointment appointment = Appointment.builder()
                        .doctor(doctor)
                        .patient(patient)
                        .dateTime(appointmentDto.getDateTime())
                        .durationMinutes(appointmentDto.getDoctor().getDurationEachConsult())
                        .status(Status.SCHEDULED)
                        .build();

                return repository.save(appointment);
            }
        }

        throw new RuntimeException("Horário não disponível.");
    }
}
