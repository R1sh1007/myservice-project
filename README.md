# Microservices Authentication System

This project is a Spring Boot based microservices application.  
The main purpose of this project is to understand and implement a real-world authentication and authorization flow using JWT, API Gateway, and independent services.

## Project Overview

The application follows a microservice architecture where each service has its own responsibility.

Current services:

- Auth Service
- API Gateway
- User Service
- Service Registry

The Auth Service handles user authentication, JWT generation, refresh token management, and token validation.

API Gateway works as a single entry point for all client requests and validates every protected request before forwarding it to other services.


## Authentication Flow

1. User sends login request with username and password.

2. Auth Service verifies user credentials from the database.

3. After successful authentication:
   - Access Token is generated
   - Refresh Token is generated
   - Refresh Token is stored in database

4. Client sends the JWT token with every protected API request.

5. API Gateway intercepts the request.

6. Gateway validates token using Auth Service.

7. If token is valid, request is forwarded to the required microservice.

8. If token is invalid or expired, the request is rejected.


## JWT Security Features

Implemented features:

- User Registration
- User Login
- Password Encryption using BCrypt
- JWT Access Token
- Refresh Token
- Token Validation API
- Logout with Token Blacklist
- Change Password
- Global Exception Handling

## Technology Stack

- Java
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- Spring Data JPA
- PostgreSQL
- JWT
- BCrypt
- Maven
- Lombok

  

## Learning Purpose

This project is created to practice production-level microservice concepts:
- Secure API communication
- Centralized authentication
- JWT based authorization
- Service-to-service communication
- Clean project structure
- Real backend development flow
More services and improvements will be added as the project grows.



POST
