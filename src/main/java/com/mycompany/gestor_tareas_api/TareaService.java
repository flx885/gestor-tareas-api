package com.mycompany.gestor_tareas_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // marca esta clase como la capa de lógica de negocio, entre el Controller y el Repository
public class TareaService {

    @Autowired // Spring inyecta automáticamente una instancia de TareaRepository, sin necesidad de new
    private TareaRepository tareaRepository;

    public List<Tarea> listartodas() { // devuelve todas las tareas guardadas
        return tareaRepository.findAll(); // findAll() ya viene hecho por JpaRepository
    }

    public Tarea guardar(Tarea tarea) { // guarda una tarea nueva o actualiza una existente
        return tareaRepository.save(tarea); // save() decide INSERT o UPDATE según si el id ya existe
    }

    @Autowired // Spring inyecta también DiaRepository, necesario para buscar el día real al asignarlo
    private DiaRepository diaRepository;

    public Tarea asignarDia(int tareaId, int diaId) { // asigna un Dia existente a una Tarea existente
        Tarea tareaReal = tareaRepository.findById(tareaId) // busca en la base de datos la tarea con este id
                .orElseThrow(() -> new IllegalArgumentException("tarea no encontrada")); // si no existe, avisa con un error

        Dia diaREal = diaRepository.findById(diaId) // busca en la base de datos el día con este id
                .orElseThrow(() -> new IllegalArgumentException("dia no encontrada")); // si no existe, avisa con un error

        tareaReal.setDia(diaREal); // a esta tarea real, le cambio su atributo "dia" por el día que acabo de encontrar

        return tareaRepository.save(tareaReal); // guardo la tarea en la base de datos, ya con el cambio hecho
    }

    public void repartirTareas(int semanaId) {
        // semanaId es una casilla vacía: se rellena con el número real cuando alguien llame a este método desde fuera

        List<Dia> dias = diaRepository.findBySemanaId(semanaId); // busco en la tabla "dia" todas las filas cuya semana_id coincida con este número: me da los 5 días de esa semana
        List<Tarea> tareasSinAsignar = tareaRepository.findByDiaIsNull(); // busco en la tabla "tarea" todas las filas cuyo dia_id esté vacío: tareas sueltas, de cualquier semana

        // construyo un diccionario: para cada Dia, cuento cuántas tareas tiene YA asignadas en este momento
        Map<Dia, Integer> contador = new HashMap<>();
        for (Dia d : dias) {
            contador.put(d, tareaRepository.findByDia(d).size()); // busco cuántas tareas tiene este día concreto, y lo guardo en el diccionario
        }

        // recorro cada tarea sin asignar, una por una, para repartirla
        for (Tarea tarea : tareasSinAsignar) {

            // antes de buscar, reseteo estas dos variables: cada tarea necesita su propia búsqueda desde cero
            Dia diaConMenos = null;
            int minimoTareas = Integer.MAX_VALUE; // número enorme, para que el primer día que compare ya sea "menor"

            for (Dia diaActual : dias) { // recorro la lista de días EN ORDEN (Día 1, 2, 3, 4, 5), a diferencia del Map, que no garantiza orden
                int numeroTareas = contador.get(diaActual); // le pregunto al diccionario cuántas tareas tiene este día concreto

                if (numeroTareas < minimoTareas) { // si este día tiene menos tareas que el mínimo encontrado hasta ahora
                    diaConMenos = diaActual;        // lo guardo como el mejor candidato
                    minimoTareas = numeroTareas;    // y actualizo también el número mínimo
                }
            }
            // al salir de este for, diaConMenos ya tiene el día definitivo con menos tareas, para ESTA tarea

            tarea.setDia(diaConMenos);                                     // 1. le cambio el atributo "dia" a esta tarea
            tareaRepository.save(tarea);                                   // 2. guardo la tarea en la base de datos, ya actualizada
            contador.put(diaConMenos, contador.get(diaConMenos) + 1);      // 3. sumo 1 al contador de ese día, para que la SIGUIENTE tarea del bucle ya lo tenga en cuenta
        }
    }

    public Tarea completar(int tareaId) { // marca una tarea existente como completada
        Tarea tareaReal = tareaRepository.findById(tareaId) // busco la tarea real por su id
                .orElseThrow(() -> new IllegalArgumentException("tarea no encontrada")); // si no existe, aviso con un error

        tareaReal.setCompletada(true); // cambio su atributo completada a true

        return tareaRepository.save(tareaReal); // guardo la tarea ya actualizada
    }
    
    public Tarea eliminar (int tareaId){
        Tarea tareaDel = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new IllegalArgumentException("tarea no encontrada")); // si no existe, aviso con un error
       
       tareaRepository.deleteById(tareaId);
       return tareaDel;
    }
    
}
