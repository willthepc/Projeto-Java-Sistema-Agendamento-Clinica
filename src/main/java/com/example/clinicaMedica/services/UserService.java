package com.example.clinicaMedica.services;

import com.example.clinicaMedica.dto.UserDto;
import com.example.clinicaMedica.enums.Role;
import com.example.clinicaMedica.entities.User;
import com.example.clinicaMedica.repository.UserRepository;
import com.example.clinicaMedica.services.exception.PermissionDenied;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public User create(UserDto userDto) {
        Role newRole;
        if (userDto.getRole() != Role.ADMIN) {
            newRole = userDto.getRole();
        } else {
            throw new PermissionDenied("Permission Denied!");
        }
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(newRole)
                .build();
            return repository.save(user);
    }

    public User update(Long id, UserDto newUserDto) {
        User user = repository.getReferenceById(id);
        user.setName(newUserDto.getName());
        user.setEmail(newUserDto.getEmail());
        user.setPassword(newUserDto.getPassword());
        Role role = newUserDto.getRole();
        if (role == Role.ADMIN) {
            user.setRole(Role.ADMIN);
        } else if (role == Role.PATIENT) {
            user.setRole(Role.PATIENT);
        } else {
            user.setRole(Role.DOCTOR);
        }
        return repository.save(user);
    }

    public void delete(Long id) {
        User user = repository.getReferenceById(id);
        repository.delete(user);
    }
}
