package com.mycompany.gestor_tareas_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Esta es la clase de arranque de todo el proyecto — el primer punto que se ejecuta.
 * Levanta el servidor web, conecta con MySQL, y activa todos los Controllers,
 * Services y Repositories que ha ido encontrando por el proyecto.
 */
@SpringBootApplication // activa toda la configuración automática de Spring Boot
public class GestorTareasApiApplication {

	public static void main(String[] args) { // punto de entrada estándar de Java
		SpringApplication.run(GestorTareasApiApplication.class, args); // arranca todo el motor de Spring Boot
	}

}
