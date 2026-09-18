package com.mycompany.gestor_tareas_api;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Interfaz vacía: Spring genera automáticamente todo lo necesario para hablar
 * con la tabla "semana" de la base de datos (guardar, buscar, listar, borrar...).
 */
public interface SemanaRepository extends JpaRepository<Semana, Integer>{ // Semana es la entidad que gestiona, Integer es el tipo de su id

}
