package com.example.clinicaMedica.services;

import com.example.clinicaMedica.dto.AvailabilityDto;
import com.example.clinicaMedica.entities.Availability;
import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.repository.AvailabilityRepository;
import com.example.clinicaMedica.repository.DoctorRepository;
import com.example.clinicaMedica.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    public AvailabilityRepository availabilityRepository;

    @Autowired
    public DoctorRepository doctorRepository;

    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    public Availability findById(Long id) {
        return availabilityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Not found"));
    }

    public Availability create(Long doctorId, AvailabilityDto availabilityDto) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ObjectNotFoundException("Doctor Not found"));

        Availability availability = Availability.builder()
                .doctor(doctor)
                .dayOfWeek(availabilityDto.getDayOfWeek())
                .startTime(availabilityDto.getStartTime())
                .endTime(availabilityDto.getEndTime())
                .build();

        return availabilityRepository.save(availability);
    }

    public Availability update(Long id, AvailabilityDto availabilityDto) {
        Availability availability = availabilityRepository.getReferenceById(id);

        availability.setDayOfWeek(availabilityDto.getDayOfWeek());
        availability.setStartTime(availabilityDto.getStartTime());
        availability.setEndTime(availabilityDto.getEndTime());

        return availabilityRepository.save(availability);
    }

    public void delete(Long id) {
        Availability availability = availabilityRepository.getReferenceById(id);
        availabilityRepository.delete(availability);
    }

    public List<Availability> findDoctorById(Long id) {
        return availabilityRepository.findByDoctorById(id);
    }

    public List<LocalTime> findAvailableSlots(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ObjectNotFoundException("Doctor not found!"));

        DayOfWeek diaDaSemana = date.getDayOfWeek();
        Integer duracao = doctor.getDurationEachConsult();

        List<Availability> janelas = availabilityRepository.findByDoctorById(doctorId);
        List<LocalTime> slots = new ArrayList<>();

        for (Availability janela : janelas) {
            for (DayOfWeek day : janela.getDayOfWeek()) {
                if (day == diaDaSemana) {
                    LocalTime atual = janela.getStartTime();

                    while (!atual.plusMinutes(duracao).isAfter(janela.getEndTime())) {
                        slots.add(atual);
                        atual = atual.plusMinutes(duracao);
                    }
                    break;
                }

            }
        }
        return slots;
    }
}