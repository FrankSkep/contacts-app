# Agenda de Contactos

## Descripción

Agenda de contactos web desarrollada en Java 21 con Spring Boot 3.3.2, utilizando la arquitectura MVC (
Modelo-Vista-Controlador).

## Características

- **Autenticación de usuarios** con Spring Security.
- **Cifrado de contraseñas** utilizando BCrypt.
- **Gestión de contactos**: Crear, editar y eliminar contactos personales.
- **Eliminación de cuenta**: Los usuarios pueden eliminar su cuenta y todos sus contactos asociados.
- **Interfaz responsive**: Construida con Bootstrap y Thymeleaf.
- **Persistencia de datos**: Implementada con Hibernate y MySQL.

## Tecnologías

- Java 21
- Spring Boot 3.3.2
- Spring Security
- BCrypt
- Hibernate
- Thymeleaf
- Bootstrap
- MySQL

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/FrankSkep/Agenda_Contactos.git
    ```
2. Accede al directorio del proyecto:
    ```sh
    cd Agenda_Contactos
    ```
3. Compila el proyecto:
    ```sh
    mvn clean install
    ```
4. Crea la base de datos `agenda` en tu MySQL.

5. Configura las credenciales de la base de datos en `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/agenda
    spring.datasource.username=TU_USUARIO
    spring.datasource.password=TU_CONTRASEÑA
    ```

## Ejecución

1. Inicia la aplicación:
    ```sh
    mvn spring-boot:run
    ```
2. Abre el navegador en `http://localhost:8080`.

## Estructura del Proyecto

### Controladores

- **`AuthController`**: Maneja autenticación de usuarios.
- **`ContactoController`**: Gestiona los contactos.
- **`UsuarioController`**: Maneja operaciones de usuarios.

### Entidades

- **`Contacto`**: Representa un contacto.
- **`Usuario`**: Representa un usuario.

### Servicios

- **`ContactoService`**: Lógica para la gestión de contactos.
- **`UsuarioService`**: Lógica para la gestión de usuarios.

### Seguridad

- **`SecurityConfig`**: Configura la seguridad con Spring Security.

### DTOs

- **`UsuarioDTO`**: Data Transfer Object para el registro de usuarios.

## Requisitos Previos

- Java 21
- Maven
- MySQL