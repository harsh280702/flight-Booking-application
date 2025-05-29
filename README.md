## 📌 Overview

The **Flight Booking Application** is a microservices-based system developed using **Java 17** and **Spring Boot**. It is designed to handle end-to-end flight booking operations including user registration, flight search, ticket generation, and booking management. The architecture follows a modular approach, making it scalable and easy to maintain.

This application demonstrates key enterprise-level concepts such as:

- ✅ Service discovery using **Eureka Server**
- ✅ Centralized configuration with **Spring Cloud Config**
- ✅ API Gateway routing via **Spring Cloud Gateway**
- ✅ Clean separation of concerns using independent services
- ✅ RESTful communication between services

### 🔧 Microservices in the Project

| Microservice       | Description                                           |
|--------------------|-------------------------------------------------------|
| `ApiGate`          | Acts as the API Gateway for routing client requests   |
| `BookingApi`       | Handles all booking-related operations                |
| `Config-server`    | Centralized configuration management                  |
| `FlightApi`        | Manages flight details and search functionality       |
| `ServicesResgsitry`| Eureka Server for service discovery                   |
| `TicketApi`        | Generates and manages ticket information              |
| `UserApi`          | Manages user authentication and registration          |

Each service is independently deployable and communicates over HTTP using REST APIs.

> This project is ideal for learning Spring Boot microservices architecture, inter-service communication, and cloud-native development using Java.
