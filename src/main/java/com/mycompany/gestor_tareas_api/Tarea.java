package com.mycompany.gestor_tareas_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Esta clase representa una "Tarea" concreta (ej: "Estudiar Spring Security").
 * Una Tarea puede crearse SIN día asignado (para asignarlo después, manual o
 * automáticamente), y puede marcarse como completada o no. Se guarda como
 * una tabla real en la base de datos.
 */
@Entity
public class Tarea {

    @Id // marca este atributo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // el id se genera solo, de forma autoincremental
    private int id;

    @NotBlank // valida que el título no llegue vacío, nulo, ni solo espacios
    private String titulo;

   private String descripcion; // sin validación: puede quedar vacía sin problema

   private boolean completada; // true = ya está hecha, false = pendiente

   @ManyToOne // muchas Tareas pueden pertenecer al mismo Dia
   // sin @NotNull a propósito: una Tarea puede crearse sin día asignado todavía
    private Dia dia;

   public Tarea (){ // constructor vacío, obligatorio para que Hibernate pueda construir el objeto internamente

   }

    public Tarea (int id, String titulo, String descripcion, boolean completada, Dia dia){ // constructor con los datos reales de una tarea
        this.setId(id);
        this.setDescripcion(descripcion);
        this.setCompletada(completada);
        this.setDia(dia);
        this.setTitulo(titulo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()){ // segunda capa de validación, además de @NotBlank
            throw new IllegalArgumentException("titulo ilegal");
        }
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() { // getter de un boolean: se nombra "is", no "get"
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        // sin validación de null a propósito: permite dejar la tarea sin día asignado
        this.dia = dia;
    }

}