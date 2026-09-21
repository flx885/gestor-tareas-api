package com.mycompany.gestor_tareas_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice; // le dice a Spring: vigila TODOS los controladores y avísame si salta un error

@RestControllerAdvice // esta clase intercepta las excepciones que se lancen en cualquier controlador de la app
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class) // captura los errores que lanzo yo a mano con "throw new IllegalArgumentException(...)"
    public ResponseEntity<String> manejarIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // responde 400 con el mensaje exacto del throw
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // captura los fallos que detecta @Valid al comprobar anotaciones como @NotBlank o @Positive
    public ResponseEntity<String> manejarValidacion(MethodArgumentNotValidException e) {
        String mensaje = e.getBindingResult().getFieldError().getDefaultMessage(); // saca el mensaje del primer campo que falló la validación
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje); // responde 400 con ese mensaje
    }
}
