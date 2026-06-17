E-Commerce API

API REST desarrollada con Java, Spring Boot y MySQL para la gestión de un sistema de comercio electrónico.

Tecnologías utilizadas

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Swagger / OpenAPI
* Git & GitHub

Funcionalidades implementadas

Categorías

* Crear categoría
* Obtener categorías
* Obtener categoría por ID
* Actualizar categoría
* Eliminar categoría

Productos

* Crear producto
* Obtener productos
* Obtener producto por ID
* Obtener productos por categoría
* Actualizar producto
* Eliminar producto

Carrito de compras

* Crear carrito
* Agregar productos al carrito
* Consultar contenido del carrito

Infraestructura

* Validación de datos mediante Bean Validation
* Manejo global de excepciones
* DTOs para entrada y salida de datos
* Arquitectura en capas (Controller, Service, Repository)
* Persistencia mediante Spring Data JPA e Hibernate
* Documentación interactiva con Swagger

Estado del proyecto

Proyecto en desarrollo activo.

Próximas funcionalidades

* Gestión de pedidos (Order y OrderItem)
* Actualización y eliminación de productos del carrito
* Autenticación y autorización con Spring Security y JWT
* Gestión de usuarios y roles
* Tests unitarios
* Docker

Cómo ejecutar el proyecto

1. Clonar el repositorio

git clone URL_DEL_REPOSITORIO

2. Configurar la base de datos MySQL en:

application.properties

3. Ejecutar la aplicación

mvn spring-boot:run

4. Acceder a Swagger

http://localhost:8080/swagger-ui/index.html
