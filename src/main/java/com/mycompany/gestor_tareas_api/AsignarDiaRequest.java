package com.mycompany.gestor_tareas_api;

/*
 * Esta clase es solo un "sobre" simple, para recibir un único dato desde fuera (un número de día).
 * No representa ninguna tabla, no tiene lógica: solo sirve para que Spring pueda convertir
 * el JSON {"diaId": 3} en un objeto Java que TareaController pueda usar.
 */
public class AsignarDiaRequest {

    private int diaId; // aquí se guarda el número del día que llega desde fuera

    public int getDiaId() { // devuelve el número guardado
        return diaId;
    }

    public void setDiaId(int diaId) { // rellena el número (Spring lo hace solo, a partir del JSON)
        this.diaId = diaId;
    }

}
