package com.mycompany.gestor_tareas_api;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Interfaz vacía: Spring genera automáticamente todo lo necesario para hablar
 * con la tabla "dia" de la base de datos (guardar, buscar, listar, borrar...).
 * Solo se le añade un método extra, para buscar los días de una semana concreta.
 */
public interface DiaRepository extends JpaRepository<Dia, Integer>{ // Dia es la entidad que gestiona, Integer es el tipo de su id

    List<Dia> findBySemanaId(int semanaId); // Spring, solo leyendo este nombre, genera la consulta: "trae todos los Dia cuya semana tenga este id"

}
