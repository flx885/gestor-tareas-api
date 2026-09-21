# 🏴‍☠️ Gestor de Tareas API

API REST hecha con Spring Boot para organizar tareas semanales, con reparto automático y una interfaz web con temática pirata.

🔗 **Demo en vivo:** https://flx885.github.io/gestor-tareas-api/interfaz/gestor_tareas.html

## Funcionalidades

- Estructura jerárquica: **Semana → Día → Tarea**
- Al crear una semana, se generan automáticamente sus 5 días (Lunes a Viernes)
- Asignación manual de tareas a un día concreto
- **Reparto automático**: distribuye las tareas sin asignar entre los días con menos carga
- Marcar tareas como completadas
- Renombrar semanas
- Eliminar tareas
- Autenticación básica mediante contraseña compartida
- Manejo centralizado de errores con respuestas limpias (`GlobalExceptionHandler`)

## Tecnologías

- Java 21 + Spring Boot
- Spring Data JPA / Hibernate
- MySQL (desplegado en Railway)
- HTML, CSS y JavaScript puro para la interfaz
- Despliegue: Railway (backend + base de datos) y GitHub Pages (interfaz)

## Arquitectura

Cada `Semana` tiene varios `Dia`, y cada `Dia` puede tener varias `Tarea`. Las tareas sin asignar se reparten automáticamente entre los días con menos tareas, en orden, para equilibrar la carga de trabajo.
