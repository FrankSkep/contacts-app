# Contacts App

## Description

A web application for managing contacts, developed with Java, Spring Boot, and MySQL, using the MVC architecture.

## Features

- **User Authentication**: Basic session-based authentication using Spring Security.
- **Contact Management**: Create, edit, and delete personal contacts.
- **Account Deletion**: Users can delete their account and all associated contacts.
- **Responsive Interface**: Built with Bootstrap and Thymeleaf.
- **Data Persistence**: Implemented with MySQL and Hibernate.

## Technologies

- Java 21
- Spring Boot 3.3.2
- Spring Security
- Hibernate
- Thymeleaf
- Bootstrap
- MySQL

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/FrankSkep/contacts-app
    ```
2. Navigate to the project directory:
    ```sh
    cd contacts-app
    ```
3. Compile the project:
    ```sh
    mvn clean install
    ```
4. Create a database in MySQL.

5. Create a `.env` file in `src/main/resources` with your MySQL credentials:
    ```properties
    DB_URL=jdbc:mysql://localhost:3306/your_database_name
    DB_USERNAME=your_mysql_username
    DB_PASSWORD=your_mysql_password
    ```

## Running the Application

1. Start the application:
    ```sh
    mvn spring-boot:run
    ```
2. Open your browser and go to `http://localhost:8090`.