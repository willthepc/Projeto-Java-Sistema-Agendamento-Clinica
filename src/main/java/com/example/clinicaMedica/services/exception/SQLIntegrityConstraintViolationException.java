package com.example.clinicaMedica.services.exception;

public class SQLIntegrityConstraintViolationException extends RuntimeException {
    public SQLIntegrityConstraintViolationException(String message) {
        super(message);
    }
}
