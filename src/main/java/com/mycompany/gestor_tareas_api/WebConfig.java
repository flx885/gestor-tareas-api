package com.mycompany.gestor_tareas_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Esta clase configura CORS: permite que páginas web (como tu interfaz HTML,
 * abierta directamente en el navegador) puedan hacer peticiones a esta API,
 * sin que el navegador las bloquee por seguridad.
 */
@Configuration // le dice a Spring que esta clase contiene configuración especial de la aplicación
public class WebConfig implements WebMvcConfigurer { // "implements" significa que esta clase cumple las reglas de la interfaz WebMvcConfigurer, que ya trae hecho el "esqueleto" de configuración web de Spring

    @Override // sobrescribe un método que ya existe en WebMvcConfigurer, para personalizarlo
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // aplica esta regla a TODOS los endpoints de la API (/** significa "cualquier ruta")
                .allowedOrigins("*") // permite peticiones desde CUALQUIER origen (útil en local; en producción se restringiría a un dominio concreto)
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // permite estos tipos de petición
    }
}
/*
 * @Configuration        → marca la clase como configuración especial de Spring (no un Controller ni Service normal)
 * implements            → indica que la clase cumple/sigue las reglas de una interfaz
 * @Override             → indica que este método sustituye/personaliza uno que ya existía en la interfaz o clase padre
 * CorsRegistry          → "cuaderno" que Spring da ya hecho, para apuntar las reglas de CORS
 * WebMvcConfigurer      → interfaz de Spring con varios "puntos de personalización" de la configuración web
 * registry.addMapping() → dice A QUÉ RUTAS se aplican las reglas siguientes ("/**" = todas las rutas)
 * .allowedOrigins()     → dice DESDE QUÉ SITIOS se permite hacer peticiones ("*" = desde cualquier origen)
 * .allowedMethods()     → dice QUÉ TIPOS de petición HTTP están permitidos (GET, POST, PUT, DELETE...)
 */