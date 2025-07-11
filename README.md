# ForoHub - API de Tópicos y Respuestas

Este proyecto forma parte del challenge de **Oracle Next Education** y **Alura LATAM** para la **Especialización Back-end** con **JAVA**. La propuesta consiste en construir una API robusta para la gestión de tópicos y respuestas, simulando el funcionamiento de un foro interactivo. Además, se implementa autenticación con JWT para proteger los endpoints y persistencia en base de datos con Flyway.

---

## 🚀 Funcionalidades

- ✅ Registro de nuevos tópicos por usuario y curso
- 🔎 Listado completo de tópicos registrados
- 🔍 Búsqueda de detalles de un tópico por ID
- ✏️ Actualización de los datos de un tópico existente
- 🗑️ Eliminación de tópicos por ID
- 💬 CRUD de respuestas asociadas a cada tópico
- 🔒 Autenticación mediante Spring Security + JWT
- 🔃 Validaciones con `@Valid` para entradas JSON
- 🗂️ Control de versiones de la base de datos con Flyway

---

## 📦 Endpoints Principales

| Método | URI               | Descripción                            |
|--------|------------------ |----------------------------------------|
| POST   | `/login`          | Autenticación de usuario con JWT       |
| POST   | `/topicos`        | Registro de nuevo tópico               |
| GET    | `/topicos`        | Listado de todos los tópicos paginados |
| GET    | `/topicos/{id}`   | Detalle de un tópico específico        |
| PUT    | `/topicos/{id}`   | Actualización de datos de un tópico    |
| DELETE | `/topicos/{id}`   | Eliminación de un tópico               |

> También se incluyen endpoints para respuestas, no detallados aquí para mantener conciso el resumen.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**: Lenguaje principal del desarrollo
- **Spring Boot 3.5.3**: Framework para construir aplicaciones empresariales
- **Spring Security + JWT**: Seguridad y autenticación de usuarios
- **Flyway + MySQL**: Migraciones y persistencia en base de datos
- **Lombok**: Reducción de código boilerplate
- **Jakarta Validation**: Validación de datos de entrada
- **Postman**: Herramientas para pruebas de API
