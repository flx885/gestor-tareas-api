package com.mycompany.gestor_tareas_api;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Interfaz vacía: Spring genera automáticamente todo lo necesario para hablar
 * con la tabla "tarea" de la base de datos (guardar, buscar, listar, borrar...).
 * Se le añaden dos métodos extra, necesarios para el reparto automático.
 */
public interface TareaRepository extends JpaRepository<Tarea, Integer>{ // Tarea es la entidad que gestiona, Integer es el tipo de su id


    List<Tarea> findByDiaIsNull(); // trae todas las Tareas cuyo campo "dia" esté vacío (sin asignar todavía)

    List<Tarea> findByDia(Dia dia); // trae todas las Tareas que ya pertenecen a este Dia concreto (para contarlas)
}
