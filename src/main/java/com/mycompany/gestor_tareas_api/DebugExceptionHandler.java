package com.mycompany.gestor_tareas_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DebugExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarCualquiera(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass().getName() + ": " + e.getMessage());
    }
}