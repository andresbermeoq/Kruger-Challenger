# Kruger-Challenger
Este repositorio contiene el reto establecido por Kruger para el programa Kanterita

## Autor

- [@andresbermeoq](https://github.com/andresbermeoq)

## Tech Stack

**Client:** VueJs

**Server:** Spring Boot, PostgreSQL

El Server Backend utiliza las siguientes **Librerias:**

- Spring Dev Tools
- Lombok
- Spring Jpa
- Spring Data Validation
- Spring Fox


## Tiempo estimado de trabajo

- Busqueda de la solucion optima (2 Horas).
- Creacion y normalizacion de las diferentes tablas en PostgreSQL (2 Horas).
- Creacion de los modelos dentro de Spring Boot (1 Horas).
- Creacion de los diferentes repositorios y services (2 Horas).
- Creacion de los servicios establecidos (2 Horas).
- Validaciones de los diferentes campos establecidos (1 Hora).

## Cronocrama de estimaciones

![Estimaciones](/img/estimaciones.png)

## Creacion del modelo de datos

### Analisis

- Existen dos tipos de Roles: ['ROLE_ADMIN', 'ROLE_USER']
- Por lo tanto se establece que cada usuario va a tener un solo rol ya sea Admin o User.
- Si tiene el rol usuario, se establece que pertenece a un empleado.
- El empleado establece si esta o no vacunado para especificar si el empleado ingresa o no una lista de vacunas.

![Modelo de Datos](/img/diagrama.png)

Los roles usuario estan prestablecidos dentro de `resources/import.sql`


## Funcionamiento

### Api Rest Administrador


### Errores

![Errores](/img/capturaErrores.png)


