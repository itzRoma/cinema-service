# 🎥 cinema-service 🎥

## Description

REST application that designed for a cinema tickets management.

## Business value

- The project is for a managing online cinema tickets selling.
- The customer is a cinema that wants a software to simplify this process.
- The users are people themselves who want to buy tickets for a certain movie.

## Features

- Role based authentication.
- Find user by email. (only for ADMIN)
- Create a cinema hall. (only for ADMIN)
- Get all cinema halls.
- Create a movie. (only for ADMIN)
- Create, update and delete movie sessions. (only for ADMIN)
- Find available movie sessions.
- Add movie session to a shopping cart. (only for USER)
- Get shopping cart for a current user. (only for USER)
- Complete order (buy tickets from shopping cart). (only for USER)
- Get orders history. (only for USER)

## Technologies

- Java 17
- Maven 3.8.6
- Apache Tomcat 9.0.68
- Spring Framework 5.3.20
- Spring Security 5.6.10
- Hibernate 5.6.14.Final
- MySQL 8.0.22

## How to run

### Prerequisites

- Java
- Maven
- Apache Tomcat
- MySQL database

See recommended versions in Technologies.

### Steps

- Set your values in `resources/db.properties` file.
- Set up Tomcat (version 9 and lower).
- Run the app from favorite IDE.
- Go to the http://localhost:8080.
