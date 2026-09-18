package com.mycompany.gestor_tareas_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Esta clase representa un "Día" (ej: Lunes, Martes...) dentro de una Semana concreta.
 * Cada Dia pertenece a una única Semana, y puede tener varias Tareas asignadas.
 * Se guarda como una tabla real en la base de datos.
 */
@Entity
public class Dia {

    @Id // marca este atributo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // el id se genera solo, de forma autoincremental
    private int id;

    @NotBlank // valida que el nombre no llegue vacío, nulo, ni solo espacios
    private String nombre;

    @ManyToOne // muchos Dias pueden pertenecer a la misma Semana
    @NotNull // valida que la semana no llegue nula
    private Semana semana;

    public Dia() { // constructor vacío, obligatorio para que Hibernate pueda construir el objeto internamente

    }

    public Dia(int id, String nombre, Semana semana) { // constructor con los datos reales de un día
        this.setId(id);
        this.setNombre(nombre);
        this.setSemana(semana);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) { // segunda capa de validación, además de @NotBlank
            throw new IllegalArgumentException("palabra ilegal");
        }
        this.nombre = nombre;
    }

    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        if (semana == null) { // segunda capa de validación, además de @NotNull
            throw new IllegalArgumentException("Semana no puede ser nula");
        }
        this.semana = semana;
    }

}