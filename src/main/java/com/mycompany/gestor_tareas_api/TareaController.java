package com.mycompany.gestor_tareas_api;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // esta clase responde cuando alguien visita una URL, y devuelve datos, no páginas
@RequestMapping("/tareas") // todo lo de aquí dentro empieza por /tareas
public class TareaController {

    @Autowired // Spring me da ya construido el TareaService, no tengo que crearlo yo con new
    private TareaService tareaService;

    @GetMapping // esto responde cuando alguien quiere VER la lista de tareas
    public List<Tarea> listartodas() {
        return tareaService.listartodas(); // le pido la lista al Service y la devuelvo
    }

    @PostMapping // esto responde cuando alguien quiere CREAR una tarea nueva
    public Tarea guardar(@Valid @RequestBody Tarea tarea) {
        return tareaService.guardar(tarea); // le paso la tarea al Service para que la guarde
    }

    @PutMapping("/{id}/dia") // esto responde cuando alguien quiere CAMBIAR el día de una tarea que ya existe
    public Tarea asignarDia(@PathVariable int id, @RequestBody AsignarDiaRequest request) {
        // "id" es el número de la tarea, viene escrito directamente en la URL (ej: /tareas/5/dia)
        // "request" trae el número del día nuevo, escrito dentro del JSON que envían
        return tareaService.asignarDia(id, request.getDiaId()); // le paso los dos números al Service, que hace el trabajo real
    }

    @PostMapping("/repartir/{semanaId}") // esto responde cuando alguien pide repartir automáticamente las tareas sueltas de una semana
    public void repartir(@PathVariable int semanaId) {
        // "semanaId" viene escrito directamente en la URL (ej: /tareas/repartir/1)
        tareaService.repartirTareas(semanaId); // le paso ese número al Service, que hace todo el reparto real
    }

    @PutMapping("/{id}/completar") // responde cuando alguien quiere marcar una tarea como completada
    public Tarea completar(@PathVariable int id) {
        // "id" es el número de la tarea, viene escrito directamente en la URL (ej: /tareas/3/completar)
        return tareaService.completar(id); // le paso ese número al Service, que hace el cambio real
    }
    
    
}

/*
 * GLOSARIO DE ANOTACIONES — referencia rápida
 *
 * @RestController      → marca la clase como "recibe peticiones web y responde con datos (JSON)", no páginas HTML
 * @RequestMapping("/x")→ todos los métodos de esta clase empiezan su URL por /x
 * @GetMapping          → responde cuando alguien quiere VER/LEER datos (petición GET)
 * @PostMapping         → responde cuando alguien quiere CREAR algo nuevo, o ejecutar una acción (petición POST)
 * @PutMapping          → responde cuando alguien quiere ACTUALIZAR algo que ya existe (petición PUT)
 * @RequestBody         → coge el JSON que llega en el CUERPO de la petición, y lo convierte en un objeto Java
 * @PathVariable         → coge un valor escrito directamente en la URL (dentro de unas llaves {...}), y lo mete en una variable
 * @Valid                → activa la comprobación de las anotaciones de validación (@NotBlank, @Positive...) del objeto recibido
 * @Autowired            → Spring construye e inyecta automáticamente el objeto, sin necesidad de escribir "new"
 * @Service              → marca una clase como la capa de lógica de negocio (entre Controller y Repository)
 * @Entity               → marca una clase como una tabla real de la base de datos
 * @Id                   → marca el atributo como la clave primaria de la tabla
 * @GeneratedValue        → el valor de ese atributo (normalmente el id) se genera solo, de forma autoincremental
 * @ManyToOne             → relación "muchos a uno" (ej: muchas Tareas pueden pertenecer a un mismo Dia)
 * @NotBlank             → el texto no puede ser nulo, vacío, ni solo espacios
 * @NotNull              → el valor no puede ser nulo (para objetos, fechas, etc.)
 */
