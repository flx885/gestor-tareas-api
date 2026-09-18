package com.mycompany.gestor_tareas_api;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Este Controller recibe las peticiones web relacionadas con los Días.
 * Permite ver todos los días guardados, y crear uno nuevo (aunque normalmente
 * los días se crean solos, automáticamente, al crear una Semana).
 */
@RestController // esta clase responde peticiones web y devuelve datos (JSON), no páginas
@RequestMapping("/dias") // todo lo de aquí dentro empieza por /dias
public class DiaController {


    @Autowired // Spring me da ya construido el DiaService, no tengo que crearlo yo con new
    private DiaService diaService;

    @GetMapping // responde cuando alguien quiere VER todos los días
    public List<Dia> listartodas(){
        return diaService.listartodas(); // le pido la lista al Service y la devuelvo
    }

    @PostMapping // responde cuando alguien quiere CREAR un día nuevo (poco habitual, ya que se crean solos)
    public Dia guardar (@Valid @RequestBody Dia dia){
        return diaService.guardar(dia); // le paso el día al Service para que lo guarde
    }
}
