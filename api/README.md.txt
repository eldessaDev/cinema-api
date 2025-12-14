# 🎬 Cinema API

Backend para la gestión de un sistema de cine, desarrollado con **Java** y **Spring Boot**.
Este proyecto permite administrar películas, funciones, tickets y clientes, manejando relaciones complejas y lógica de negocio.

## 🚀 Tecnologías Usadas
* **Java 17+** (Lenguaje principal)
* **Spring Boot 3** (Framework Backend)
* **Spring Data JPA** (Persistencia de datos)
* **MySQL** (Base de datos relacional)
* **Maven** (Gestor de dependencias)

## ⚙️ Funcionalidades Principales

### 1. Gestión de Películas y Funciones (Movies & Showtimes)
* CRUD completo de Películas.
* Creación de Funciones (`Showtime`) asignadas a una película, con precio y horario.

### 2. Gestión de Tickets (Ventas)
* Venta de tickets validando disponibilidad de asientos.
* **Validación de lógica de negocio:** No permite vender el mismo asiento dos veces para la misma función.
* Asignación automática de tickets a Clientes.

### 3. Gestión de Clientes (Customers)
* Registro y actualización de clientes con validación de email único.
* **Historial de Compras:** Consulta de todos los tickets adquiridos por un cliente específico.
* **Calculadora de Gastos:** Endpoint inteligente que calcula el total de dinero invertido por un cliente en el cine.

## 📡 Endpoints Destacados

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/tickets` | Vende un nuevo ticket (Valida asiento y cliente). |
| `GET` | `/api/tickets/customer/{id}` | Muestra el historial de compras de un cliente. |
| `GET` | `/api/tickets/customer/{id}/total` | Calcula el total gastado por el cliente. |

---
*Desarrollado por [eldessaDev] - 2025*