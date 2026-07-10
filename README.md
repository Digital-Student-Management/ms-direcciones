# 📍 ms-direcciones — Jerarquía Geográfica y Direcciones

Microservicio encargado de la **jerarquía geográfica** (país → región → ciudad → comuna) y de las
**direcciones**, usadas por los formularios del sistema.

> Parte del proyecto GED. Para ejecutar **todo el sistema**, ver el [README raíz](../README.md).

---

## ⚙️ Datos técnicos

| | |
|---|---|
| **Puerto** | `8083` |
| **Base de datos** | `ms_direcciones_db` (MySQL, se crea automáticamente) |
| **Stack** | Spring Boot 4 · Java 21 · Spring Data JPA · springdoc (Swagger) |

---

## 📡 Endpoints principales

Cada recurso ofrece operaciones básicas (`GET`, `GET/{id}`, `POST`, `DELETE/{id}`):

| Recurso | Ruta base |
|---------|-----------|
| País | `/pais` |
| Región | `/region` |
| Ciudad | `/ciudad` |
| Comuna | `/comuna` |
| Dirección | `/direccion` |

> Estas rutas se exponen **sin prefijo `/api`**.

---

## ▶️ Ejecución

```bash
mvnw.cmd spring-boot:run     # Windows
./mvnw spring-boot:run       # Linux / macOS
```

- Documentación Swagger: **http://localhost:8083/swagger-ui.html**
