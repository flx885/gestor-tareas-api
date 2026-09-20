package com.mycompany.gestor_tareas_api;

import jakarta.servlet.Filter; // interfaz que me permite interceptar TODAS las peticiones antes de que lleguen a un controlador
import jakarta.servlet.FilterChain; // representa "el resto del camino" que sigue la petición si la dejo pasar
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest; // la petición que llega, en su forma más genérica
import jakarta.servlet.ServletResponse; // la respuesta que voy a devolver, en su forma más genérica
import jakarta.servlet.http.HttpServletRequest; // versión "HTTP" de la petición, con métodos útiles como getHeader()
import jakarta.servlet.http.HttpServletResponse; // versión "HTTP" de la respuesta, con métodos como setStatus()
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component; // le digo a Spring que cree esta clase sola y la use automáticamente

@Component // Spring Boot detecta esta clase al arrancar y la aplica a TODAS las peticiones, sin que yo tenga que registrarla a mano
public class PasswordFilter implements Filter { // implemento Filter para poder interceptar peticiones antes de los controladores

    @Value("${app.password}") // Spring me inyecta aquí el valor de app.password desde application.properties (o de Railway en producción)
    private String passwordCorrecta;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (request.getMethod().equals("OPTIONS")) {
            chain.doFilter(req, resp);
            return;
        }

        String passwordRecibida = request.getHeader("X-App-Password");

        if (passwordCorrecta.equals(passwordRecibida)) {
            chain.doFilter(req, resp);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}