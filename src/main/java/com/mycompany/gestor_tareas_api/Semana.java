package com.mycompany.gestor_tareas_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/*
 * Esta clase representa una "Semana" (ej: "Semana 1 - Septiembre").
 * Es la entidad de nivel más alto del proyecto: cada Semana puede tener varios Dias,
 * y cada Dia puede tener varias Tareas. Se guarda como una tabla real en la base de datos.
 */
@Entity
public class Semana {

    @Id // marca este atributo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // el id se genera solo, de forma autoincremental
    private int id;

    @NotBlank // valida que el nombre no llegue vacío, nulo, ni solo espacios
    private String nombre;


    public Semana() { // constructor vacío, obligatorio para que Hibernate pueda construir el objeto internamente

    }

    public Semana (int id, String nombre){ // constructor con los datos reales de una semana
        this.setId(id);
        this.setNombre(nombre);
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
       if (nombre == null || nombre.isBlank()){ // segunda capa de validación, además de @NotBlank
                    throw new IllegalArgumentException("palabra ilegal");
       }
        this.nombre = nombre;
    }

}
