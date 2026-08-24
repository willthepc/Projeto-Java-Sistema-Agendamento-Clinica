package com.example.clinicaMedica.entities;

import com.example.clinicaMedica.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true) //Unique para valores únicos
    @EqualsAndHashCode.Include
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
