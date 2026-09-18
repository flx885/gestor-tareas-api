package com.mycompany.gestor_tareas_api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Capa intermedia entre SemanaController y SemanaRepository.
 * Aquí está la lógica especial del proyecto: cuando se guarda una Semana nueva,
 * este Service crea automáticamente sus 5 Dias (Lunes-Viernes), sin que haya
 * que crearlos a mano cada vez.
 */
@Service // marca esta clase como la capa de lógica de negocio
public class SemanaService {

    @Autowired // Spring inyecta automáticamente una instancia de SemanaRepository, sin necesidad de new
    private SemanaRepository semanaRepository;

    @Autowired // Spring inyecta también DiaRepository, necesario para crear los 5 días automáticamente
    private DiaRepository diaRepository;

    public List<Semana> listartodas() { // devuelve todas las semanas guardadas
        return semanaRepository.findAll(); // findAll() ya viene hecho por JpaRepository
    }

    public Semana guardar(Semana semana) { // guarda una semana nueva, y crea sus 5 días automáticamente
        Semana semanaGuardar = semanaRepository.save(semana); // guardo la semana primero, para que tenga su id real

        for (int i = 1; i <= 5; i++) { // repito esto exactamente 5 veces (i = 1, 2, 3, 4, 5)
            diaRepository.save(new Dia(0, "Día " + i, semanaGuardar)); // creo un Dia nuevo, con nombre "Día 1", "Día 2"..., enlazado a la semana recién guardada
        }

        return semanaGuardar; // devuelvo la semana ya creada, con su id real
    }

    public Semana renombrar(int semanaId, String nuevoNombre) {
        Semana semanaReal = semanaRepository.findById(semanaId)
                .orElseThrow(() -> new IllegalArgumentException("semana no encontrada"));
        semanaReal.setNombre(nuevoNombre);
        return semanaRepository.save(semanaReal);
    }
}
