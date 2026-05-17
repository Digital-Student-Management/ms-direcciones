# 📍 ms-direcciones

Microservicio independiente del sistema escolar del **Colegio Bernardo O'Higgins de Coquimbo**, encargado de la gestión de la **jerarquía geográfica** utilizada en el sistema.

Administra las entidades `Pais`, `Region`, `Ciudad`, `Comuna` y `Direccion`, exponiendo una API REST que permite crear, consultar y eliminar registros en cada nivel de la jerarquía. Se comunica de forma **desacoplada** con otros microservicios del sistema (p. ej. `ms-estudiantes`, `ms-profesores`) únicamente mediante **IDs de dirección**, sin dependencias de clases compartidas ni bases de datos comunes.

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión / Detalle |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.6 |
| Maven | Wrapper incluido (`mvnw`) |
| MySQL | 8.x (base de datos relacional) |
| Spring Data JPA | Incluido en Spring Boot Starter |
| Hibernate | DDL automático (`update`) |
| Lombok | Reducción de boilerplate (getters, setters, constructores) |
| Jakarta Validation | Validación de campos en DTOs y entidades |
| SpringDoc OpenAPI | 3.0.2 — documentación Swagger UI en `/swagger-ui.html` |
| Spring DevTools | Recarga en caliente durante el desarrollo |

---

## 📁 Estructura del Proyecto

```
ms-direcciones/
├── peticiones.rest                          ← Pruebas REST Client (VS Code)
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/ms_sistemaEscolar/ms_direcciones/
        │       ├── MsDireccionesApplication.java   ← Clase principal
        │       ├── controller/
        │       │   ├── PaisController.java
        │       │   ├── RegionController.java
        │       │   ├── CiudadController.java
        │       │   ├── ComunaController.java
        │       │   └── DireccionController.java
        │       ├── models/
        │       │   ├── entities/
        │       │   │   ├── Pais.java
        │       │   │   ├── Region.java
        │       │   │   ├── Ciudad.java
        │       │   │   ├── Comuna.java
        │       │   │   └── Direccion.java
        │       │   └── dto/
        │       │       ├── PaisDTO.java
        │       │       ├── RegionDTO.java
        │       │       ├── CiudadDTO.java
        │       │       ├── ComunaDTO.java
        │       │       └── DireccionDTO.java
        │       ├── repositories/
        │       │   ├── PaisRepository.java
        │       │   ├── RegionRepository.java
        │       │   ├── CiudadRepository.java
        │       │   ├── ComunaRepository.java
        │       │   └── DireccionRepository.java
        │       └── services/
        │           ├── PaisService.java
        │           ├── RegionService.java
        │           ├── CiudadService.java
        │           ├── ComunaService.java
        │           └── DireccionService.java
        └── resources/
            └── application.properties
```

---

## 🗃️ Modelo de Datos

Las cinco entidades forman una cadena jerárquica de tipo **Many-to-One** descendente. Cada nivel referencia al nivel superior a través de una clave foránea:

```
Pais
 └── Region      (id_pais   → Pais)
      └── Ciudad (id_region → Region)
           └── Comuna       (id_ciudad → Ciudad)
                └── Direccion            (id_comuna → Comuna)
```

| Entidad | Tabla MySQL | Campos principales |
|---|---|---|
| `Pais` | `pais` | `id_pais`, `nombre` |
| `Region` | `region` | `id_region`, `nombre`, `id_pais` |
| `Ciudad` | `ciudad` | `id_ciudad`, `nombre_ciudad`, `id_region` |
| `Comuna` | `comuna` | `id_comuna`, `nombre`, `id_ciudad` |
| `Direccion` | `direccion` | `id_direccion`, `calle`, `numero`, `departamento_villa`, `id_comuna` |

> Los nombres de columnas siguen la convención **snake_case** definida mediante `@Column(name = "...")` en cada entidad JPA.

---

## ⚙️ Configuración y Ejecución

### Requisitos previos

- Java 21 instalado y configurado en `JAVA_HOME`
- MySQL 8.x corriendo en `localhost:3306`
- Usuario `root` con acceso para crear bases de datos

### Propiedades (`application.properties`)

```properties
spring.application.name=ms-direcciones
server.port=8082

spring.datasource.url=jdbc:mysql://localhost:3306/ms_direcciones_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=<tu_contraseña>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> La propiedad `createDatabaseIfNotExist=true` crea automáticamente la base de datos **`ms_direcciones_db`** si no existe.

### Iniciar el microservicio

```bash
# Con Maven Wrapper (recomendado)
./mvnw spring-boot:run

# O en Windows
mvnw.cmd spring-boot:run
```

El servidor quedará disponible en: **`http://localhost:8082`**

---

## 🔌 Endpoints disponibles

Cada entidad expone los mismos 4 endpoints REST bajo su ruta base:

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/{entidad}` | Crear un nuevo registro |
| `GET` | `/{entidad}` | Listar todos los registros |
| `GET` | `/{entidad}/{id}` | Buscar registro por ID |
| `DELETE` | `/{entidad}/{id}` | Eliminar registro por ID |

**Rutas base:** `/pais` · `/region` · `/ciudad` · `/comuna` · `/direccion`

### Ejemplo — Crear una dirección completa (flujo completo)

```http
### 1. Crear país
POST http://localhost:8082/pais
Content-Type: application/json

{ "nombre": "Chile" }

### 2. Crear región (asociada al país id=1)
POST http://localhost:8082/region
Content-Type: application/json

{ "nombre": "Coquimbo", "id_pais": 1 }

### 3. Crear ciudad (asociada a la región id=1)
POST http://localhost:8082/ciudad
Content-Type: application/json

{ "nombreCiudad": "La Serena", "id_region": 1 }

### 4. Crear comuna (asociada a la ciudad id=1)
POST http://localhost:8082/comuna
Content-Type: application/json

{ "nombre": "Coquimbo", "id_ciudad": 1 }

### 5. Crear dirección (asociada a la comuna id=1)
POST http://localhost:8082/direccion
Content-Type: application/json

{ "calle": "El Sauce", "numero": 1234, "departamentoVilla": null, "id_comuna": 1 }
```

---

## 🧪 Pruebas con REST Client

El archivo **`peticiones.rest`** en la raíz del proyecto contiene **21 peticiones** de prueba listas para ejecutar, cubriendo los 5 recursos con sus operaciones CRUD.

### Configuración en VS Code

1. Instalar la extensión [**REST Client**](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) de Huachao Mao.
2. Abrir el archivo `peticiones.rest`.
3. Hacer clic en **"Send Request"** sobre cualquier petición para ejecutarla directamente.

> ⚠️ Ejecutar las peticiones **en orden** (Pais → Region → Ciudad → Comuna → Direccion) para respetar las restricciones de integridad referencial.

---

## 📖 Documentación API (Swagger UI)

Con el microservicio corriendo, accede a la documentación interactiva generada por **SpringDoc OpenAPI**:

```
http://localhost:8082/swagger-ui.html
```

---

## 🏫 Contexto del Sistema

Este microservicio forma parte del **Sistema de Gestión Escolar** del Colegio Bernardo O'Higgins de Coquimbo. La arquitectura general está compuesta por múltiples microservicios independientes que se comunican únicamente a través de IDs, garantizando el bajo acoplamiento y la autonomía de cada módulo.

| Microservicio | Puerto | Responsabilidad |
|---|---|---|
| `ms-direcciones` | **8082** | Jerarquía geográfica |
| Otros microservicios | — | Estudiantes, Profesores, etc. |

---

*Desarrollado con Spring Boot 4 · Java 21 · Maven*
