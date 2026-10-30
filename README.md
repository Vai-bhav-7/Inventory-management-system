# Inventory Management System

A backend application built with Java and Spring Boot to manage products, inventory stock, purchases, sales, and user authentication.

## Tech Stack

- Java
- Spring Boot
- Spring Security and JWT
- PostgreSQL
- Spring Data JPA / Hibernate
- Redis
- Apache Kafka
- Maven

## Features

- User registration and login with JWT authentication
- Role-based access control
- Product management APIs
- Inventory stock-in and stock-out operations
- Purchase and sales management
- Inventory transaction history
- Redis caching
- Kafka-based product event messaging
- Request validation and centralized exception handling

## Configuration

Configure the following environment variables before running the application:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET`
- `REDIS_HOST`
- `REDIS_PORT`
- `KAFKA_BOOTSTRAP_SERVERS`

Do not commit your `.env` file or real credentials to GitHub.

## Run Locally

1. Install Java and PostgreSQL.
2. Create the `inventory_db` database.
3. Configure the required environment variables.
4. Start Redis and Kafka.
5. Run the application using the Maven wrapper:

   ```bash
   .\mvnw.cmd spring-boot:run
   ```

The application runs on port `8081` by default.

## Project Status

Core backend features implemented. Deployment and further testing are planned.
