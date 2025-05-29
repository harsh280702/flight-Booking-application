## ✈️ Flight Booking Application – Overview

The **Flight Booking Application** is a full-fledged, microservices-based web application built using **Java 17**, **Spring Boot**, and **Spring Cloud**. This project is designed to simulate a real-world airline booking platform and demonstrate the implementation of modular services that can scale independently.

This application handles various key operations such as:

- 🔐 User registration and authentication
- 🛫 Searching and managing flights
- 🎟️ Booking and issuing tickets
- 🌐 Routing requests via an API Gateway
- 🛠️ Centralized configuration management
- 🔍 Service discovery using Eureka

The architecture promotes **loose coupling**, **high cohesion**, and **scalability**, making it suitable for cloud deployment, DevOps workflows, and enterprise application development practices.

---

## 🧱 Microservices Architecture

This project is organized into several microservices. Each service is designed to handle a single responsibility and can run independently or alongside others.

| Service            | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| `ApiGate`          | Routes incoming client requests to the appropriate microservice using Spring Cloud Gateway |
| `BookingApi`       | Handles booking operations, seat availability, and booking history         |
| `Config-server`    | Centralized configuration for all microservices                            |
| `FlightApi`        | Manages flight data, including creation, search, and details               |
| `ServicesResgsitry`| Eureka Service Registry for service discovery and dynamic load balancing   |
| `TicketApi`        | Responsible for ticket issuance, retrieval, and ticket status              |
| `UserApi`          | Manages user sign-up, login, and profile management                        |

Each service is self-contained with its own controller, service, model, and repository layers.

---

## 🔧 Technologies Used

The application leverages a modern Java technology stack:

- **Java 17** – Latest long-term support (LTS) version
- **Spring Boot** – Simplifies microservice development
- **Spring Cloud** – Enables service discovery, routing, and centralized configuration
- **Eureka Server** – Service discovery for registering and discovering microservices
- **Spring Cloud Config Server** – Manages external configurations for all services
- **Maven** – Project and dependency management
- **RESTful APIs** – Used for inter-service communication
- **Lombok** – Reduces boilerplate code
- **Postman / Swagger** – (Optional) for testing and API documentation

---

## 🗂️ Key Features

### ✅ Modular Architecture
The project is split into clearly defined modules/microservices, promoting separation of concerns and modular development.

### ✅ Scalable and Maintainable
Each service can be scaled individually based on its load, making the application cloud-ready and ideal for containerization.

### ✅ Centralized Configuration
The `Config-server` provides centralized configuration management, making it easier to manage environment-specific properties across services.

### ✅ Service Discovery
The application uses `Eureka Server` for dynamically discovering and registering microservices.

### ✅ Secure Communication
Though not implemented yet, the architecture supports the integration of OAuth2, JWT, or Spring Security for secure communication between services.

---

## 📦 Project Folder Structure
flight-Booking-application/
├── ApiGate/ # API Gateway
├── BookingApi/ # Booking Microservice
├── Config-server/ # Spring Cloud Config Server
├── FlightApi/ # Flight Management Service
├── ServicesResgsitry/ # Eureka Service Registry
├── TicketApi/ # Ticket Management Service
└── UserApi/ # User Registration/Login Service
