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

/*
 * Este Controller recibe las peticiones web relacionadas con las Semanas.
 * Permite ver todas las semanas guardadas, y crear una nueva — al crearla,
 * por detrás, el Service genera automáticamente sus 5 días (Lunes-Viernes).
 */
@RestController // esta clase responde peticiones web y devuelve datos (JSON), no páginas
@RequestMapping("/semanas") // todo lo de aquí dentro empieza por /semanas
public class SemanaController {

    @Autowired // Spring me da ya construido el SemanaService, no tengo que crearlo yo con new
    private SemanaService semanaService;

    @GetMapping // responde cuando alguien quiere VER todas las semanas
    public List<Semana> listartodas() {
        return semanaService.listartodas(); // le pido la lista al Service y la devuelvo
    }

    @PostMapping // responde cuando alguien quiere CREAR una semana nueva
    public Semana guardar(@Valid @RequestBody Semana semana) {
        return semanaService.guardar(semana); // le paso la semana al Service, que la guarda y crea sus 5 días
    }

    @PutMapping("/{id}")
    public Semana renombrar(@PathVariable int id, @RequestBody RenombrarRequest request) {
        return semanaService.renombrar(id, request.getNombre());
    }

}
