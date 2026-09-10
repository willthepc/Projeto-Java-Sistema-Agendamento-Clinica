package com.example.clinicaMedica.services.exception;

public class PermissionDenied extends RuntimeException {
    public PermissionDenied(String message) {
        super(message);
    }
}
