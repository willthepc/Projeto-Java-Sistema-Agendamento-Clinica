package com.example.clinicaMedica.config;

import com.example.clinicaMedica.entities.Doctor;
import com.example.clinicaMedica.entities.Patient;
import com.example.clinicaMedica.entities.Specialty;
import com.example.clinicaMedica.enums.Role;
import com.example.clinicaMedica.entities.User;
import com.example.clinicaMedica.repository.DoctorRepository;
import com.example.clinicaMedica.repository.PatientRepository;
import com.example.clinicaMedica.repository.SpecialtyRepository;
import com.example.clinicaMedica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.print.Doc;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class Config implements CommandLineRunner {
    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private SpecialtyRepository repositorySpecialty;

    @Autowired
    private DoctorRepository repositoryDoctor;

    @Autowired
    private PatientRepository repositoryPatients;

    @Override
    public void run(String... args) throws Exception {
        repositoryUser.deleteAll();
        User u1 = new User(null, "William Nascimento", "william.nascimento@gmail.com", "nascimento123", Role.PATIENT);
        User u2 = new User(null, "Dr. Carlos Mendes",  "carlos.mendes@clinica.com",  "123456", Role.DOCTOR);
        User u3 = new User(null, "Dra. Fernanda Lima", "fernanda.lima@clinica", "09876", Role.DOCTOR);
        User u4 = new User(null, "Juliana Alves", "juliana.alves@gmail.com", "juliana.alves@gmail.com", Role.PATIENT);
        repositoryUser.saveAll(Arrays.asList(u1, u2, u3, u4));

        repositorySpecialty.deleteAll();
        Specialty s1 = new Specialty(null, "Cariologia");
        Specialty s2 = new Specialty(null, "Dermatologia");
        Specialty s3 = new Specialty(null, "Pediatria");
        repositorySpecialty.saveAll(Arrays.asList(s1, s2, s3));

        repositoryDoctor.deleteAll();
        Doctor d1 = new Doctor(null, repositoryUser.getReferenceById(u2.getId()), "12345-SP", repositorySpecialty.getReferenceById(s1.getId()), 30);
        Doctor d2 = new Doctor(null, repositoryUser.getReferenceById(u3.getId()), "67890-RJ", repositorySpecialty.getReferenceById(s2.getId()), 45);
        repositoryDoctor.saveAll(Arrays.asList(d1, d2));

        repositoryPatients.deleteAll();
        Patient p1 = new Patient(null, repositoryUser.getReferenceById(u4.getId()), LocalDate.parse("2000-09-20"), "11999999999");
        Patient p2 = new Patient(null, repositoryUser.getReferenceById(u1.getId()), LocalDate.parse("2001-02-09"), "11988888888");
        repositoryPatients.saveAll(Arrays.asList(p1, p2));

    }
}
