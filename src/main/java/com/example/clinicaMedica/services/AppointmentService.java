package com.example.clinicaMedica.services;

import com.example.clinicaMedica.dto.AppointmentDto;
import com.example.clinicaMedica.entities.Appointment;
import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.entities.Patient;
import com.example.clinicaMedica.enums.Status;
import com.example.clinicaMedica.repository.AppointmentRepository;
import com.example.clinicaMedica.repository.DoctorRepository;
import com.example.clinicaMedica.repository.PatientRepository;
import com.example.clinicaMedica.services.exception.HourException;
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
            if (horarioDesejado.equals(appointmentDto.getDateTime().toLocalTime())) {
                Appointment appointment = Appointment.builder()
                        .doctor(doctor)
                        .patient(patient)
                        .dateTime(appointmentDto.getDateTime())
                        .durationMinutes(doctor.getDurationEachConsult())
                        .status(Status.SCHEDULED)
                        .build();

                return repository.save(appointment);
            }
        }

        throw new HourException("Horário não disponível.");
    }

    public Appointment updateStatusCancel(Long id) {
        Appointment appointment = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Consulta não encontrada!"));
        if (LocalDate.now().equals(appointment.getDateTime().toLocalDate())) {
            throw new HourException("Não é possível realizar o cancelamento!");
        }
        appointment.setStatus(Status.CANCELED);
        return repository.save(appointment);
    }

    public Status statusAtual(Long id) {
        Appointment appointment = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Consulta não encontrada!"));
        return appointment.getStatus();
    }
}
