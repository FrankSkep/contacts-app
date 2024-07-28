# Agenda de Contactos

## Descripción

Aplicación de agenda de contactos desarrollada en Java 21 utilizando Spring Boot 3.3.2. La aplicación permite a los usuarios registrarse, autenticarse y gestionar sus contactos de manera segura.

## Características

- **Registro y autenticación de usuarios:** Implementado con Spring Security.
- **Seguridad de contraseñas:** Se utilizó BCrypt para encriptar las contraseñas de los usuarios, y almacenarlas de forma segura en la base de datos.
- **Gestión de contactos:** Cada usuario autenticado puede añadir, editar y eliminar sus contactos personales.
- **Eliminación de cuenta:** Cada usuario puede eliminar su cuenta si lo desea, lo cual también elimina todos los contactos registrados en su cuenta.
- **Interfaz de usuario:** Se utilizo Bootstrap para los estilos, y Thymeleaf para generar vistas dinamicas.
- **Persistencia de datos:** Se utilizo Hibernate, y una base de datos MySQL para almacenar información de usuarios y contactos.

### [Pantallas de la aplicacion](https://github.com/FrankSkep/Agenda/blob/main/assets/views.md)

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.3.2
- Spring Security
- BCrypt
- Hibernate
- Thymeleaf
- Bootstrap
- MySQL

## Estructura del Proyecto

### Controladores

- `AuthController.java`: Maneja las operaciones de autenticación.
- `ContactoController.java`: Gestiona las operaciones relacionadas con los contactos.
- `RedireccionController.java`: Controla las redirecciones dentro de la aplicación.
- `UsuarioController.java`: Maneja las operaciones relacionadas con los usuarios.

### Entidades

- `Contacto.java`: Representa la entidad de contacto.
- `Usuario.java`: Representa la entidad de usuario.

### Servicios

- `ContactoService.java`: Provee la lógica para las operaciones de contacto.
- `UsuarioService.java`: Provee la lógica para las operaciones de usuario.

### Configuración de Seguridad

- `SecurityConfig.java`: Configura la seguridad de la aplicación utilizando Spring Security.

### DTO

- `UsuarioRegDTO.java`: Data Transfer Object utilizado para el registro de usuarios.

## Requisitos Previos

- Java 21
- Maven
- MySQL

## Configuración de la Base de Datos

Configurar eliminacion en cascada manualmente:

```sql
ALTER TABLE contacto DROP FOREIGN KEY FKo2mhiinaiwtgx848vwa65vl56;
ALTER TABLE contacto ADD CONSTRAINT FKo2mhiinaiwtgx848vwa65vl56 FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE;
```
