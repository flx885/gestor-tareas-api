package com.mycompany.gestor_tareas_api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Capa intermedia entre DiaController y DiaRepository.
 * Aquí no hay lógica especial: solo lee y guarda días, tal cual.
 */
@Service // marca esta clase como la capa de lógica de negocio
public class DiaService {

    @Autowired // Spring inyecta automáticamente una instancia de DiaRepository, sin necesidad de new
    private DiaRepository diaRepository;


    public List<Dia> listartodas(){ // devuelve todos los días guardados
        return diaRepository.findAll(); // findAll() ya viene hecho por JpaRepository
    }

    public Dia guardar (Dia dia){ // guarda un día nuevo o actualiza uno existente
        return diaRepository.save(dia); // save() decide INSERT o UPDATE según si el id ya existe
    }

}
