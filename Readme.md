# Contact Management API

A small RESTful API to manage contacts, built with Java and Spring Boot.

## What it does
- CRUD (create, read, update, delete) operations for contacts via HTTP endpoints.
- Exposes endpoints such as `/contacts`.

## Tech stack
- Java
- Spring Boot
- Maven

## How to build
Run from the project root:

mvn clean package

## How to run
- From Maven: mvn spring-boot:run
- Or run the jar produced in `target`:

java -jar target/Getting-0.0.1-SNAPSHOT.jar

## Project structure (short)
- `src/main/java` – application code (controllers, config)
- `src/main/resources` – `application.properties`
- `src/test` – tests

