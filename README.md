# Kruger-Challenger
Este repositorio contiene el reto establecido por Kruger para el programa Kanterita

## Autor

- [@andresbermeoq](https://github.com/andresbermeoq)

## Tech Stack

**Client:** Postman

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

El modelo de datos se encuentra de la carpeta `DataModel`.


## Funcionamiento

Se implemento Spring Fox para la documentacion de la Api Rest, tambien dentro del archivo `resources/import.sql` se creo automaticamente un usuario admin con los siguientes valores

**username**: aBermeo

**password**: 0150384980

### Creacion de un Empleado

```json
{
    "nombre": "Carlos",
	"apellido": "Moreno",
	"email": "carlosmoreno@gmail.com",
	"cedula": "0150384982"
}
```

**Nota:** El usuario creado es la primera letra del nombre y el apellido `CMoreno` y el password es la cedula.


### Actualizacion Empleado con Vacunas

```json
{
    "fechaNacimiento": "1995-09-30",
    "telefonoCelular": "0960851986",
    "direccion": "Sangurima y Miguel Velez",
    "estadoVacunas": "Vacunado",
    "vacunas": [
        {
            "vaccineType": "Aztrazeneca",
            "vaccineDate": "2022-08-20",
            "vaccineNumber": 2
        },
        {
            "vaccineType": "Sputnik",
            "vaccineDate": "2022-08-21",
            "vaccineNumber": 1
        },
        {
            "vaccineType": "Pfizer",
            "vaccineDate": "2022-08-19",
            "vaccineNumber": 1
        }
    ]
}
```

### Actualizacion Empleado Sin Vacunas

```json
{
    "fechaNacimiento": "1996-09-29",
    "telefonoCelular": "0960851984",
    "direccion": "Huayna Capac y Remigio",
    "estadoVacunas": "No Vacunado"
}
```

## Documentacion
Se puede acceder a la documentacion de Spring Fox utilizando el siguiente enlance
link: [Swagger](http://localhost:8080/swagger-ui.html#/)

![Documentacion](/img/documentacion.png)


### Tabla de URLs Administrador
Función | URL
------------ | -------------
Login | localhost:8080/oauth/token
Creacion Empleado | localhost:8080/api/v1/employee
Borrar Empleado | localhost:8080/api/v1/employee/{id_usuario}
Lista por Tipo de Vacuna | localhost:8080/api/v1/admin/tipo
Lista por Rango de Fechas | localhost:8080/api/v1/admin/rango
Lista por Estado de Vacuna | localhost:8080/api/v1/admin/estado

### Tabla de URLs Empleado
Función | URL
------------ | -------------
Login | localhost:8080/oauth/token
Actualizar Empleado | localhost:8080/api/v1/employee/{id_usuario}

### Observaciones
El momento de insertar un nuevo usuario produce una excepcion SQL esto debido a que dentro de la table `User` se encuentra en modo `Entity`.

**Solucion Temporal (Postman):** Se puede dar un click nuevamente en el boton `SEND` y se guardaria normalmente

## Autenticacion

Se ingresa mediante la url que se describio en las tablas de funcionamiento `Login`.
En la parte de `Authorization` se tiene que validar el usuario insertado en memoria

**username**: kruger

**password**: kruger

![Authenticacion](/img/autor1.png)

Luego en la parte de body se inserta el usuario establecido por default dentro de la base de datos.

![Authenticacion](/img/autor2.png)

Finalmente se obtiene un token que se debe ser incluido en todos los `endpoints` establecidos.

![Endpoint](/img/endpoint.png)

Cada endpoint debe ser previamente autenticado usando este token.

![Endpoint](/img/end.png)

### Articulos de interes utilizado

[Authentication based in roles](https://www.devglan.com/spring-security/spring-oauth2-role-based-authorization)

[Spring Security using OAUTH2 with JWT](https://www.pixeltrice.com/spring-boot-security-using-oauth2-with-jwt/)


## Errores

![Errores](/img/capturaErrores.png)


