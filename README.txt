# EcoMarket - Arquitectura de Microservicios

Este proyecto forma parte de la evaluación para la asignatura **DSY1104 - Desarrollo Fullstack I**, y consiste en el desarrollo de un sistema distribuido basado en microservicios para la gestión de un mercado virtual.

## Estructura del Proyecto

- **microservice-compra**: Servicio para la gestión de compras (CRUD completo).
- **microservice-inventario**: Servicio para la gestión del inventario (CRUD completo).
- **microservice-config**: Servicio de configuración centralizada (Spring Cloud Config Server).
- **microservice-gateway**: Puerta de enlace API (Spring Cloud Gateway) para el enrutamiento entre servicios.
- **microservice-eureka**: Servidor Eureka para el descubrimiento de servicios.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Cloud (Eureka, Config, Gateway)
- Spring Data JPA
- Maven 4
- MySQL
- Postman
- Git

## Configuración del Entorno

1. **Base de Datos**: Crear las bases de datos `compra_db` e `inventario_db` en MySQL.
2. **Archivos de Configuración**: Las propiedades se encuentran en `application.properties` de cada microservicio.
3. **Compilación**: Desde la raíz del proyecto, ejecutar:

   ```bash
   mvn clean install
   ```

4. **Ejecución**: Ejecutar los microservicios en el siguiente orden para evitar errores de dependencia:

   - microservice-config
   - microservice-eureka
   - microservice-inventario
   - microservice-compra
   - microservice-gateway

## Funcionalidades Implementadas

- Operaciones CRUD completas para compras e inventario.
- Registro y descubrimiento de servicios con Eureka.
- Configuración externa centralizada con Spring Config Server.
- Enrutamiento dinámico de APIs con Spring Gateway.
- Integración entre microservicios mediante llamadas REST.
- Pruebas de endpoints con Postman.

## Cómo Probar

- Utiliza Postman o Swagger para realizar pruebas sobre los endpoints REST expuestos por los servicios `compra` e `inventario`.
- Verifica el registro de microservicios accediendo a `http://localhost:8761` (Eureka).
- Accede a los servicios a través del Gateway en `http://localhost:8080`.
- **Inventario**: 'http://localhost:8081/swagger-ui/index.html#/'
- **Compras**: 'http://localhost:8082/swagger-ui/index.html#/'

## Notas

- Asegúrate de que los puertos definidos no estén en uso.
- El gateway enruta las peticiones según el prefijo de cada servicio (`/compra/**`, `/inventario/**`, etc.).

---


