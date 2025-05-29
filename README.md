 Flight Booking Application – Project Overview
The Flight Booking Application is a modular, microservices-based web application designed to manage various aspects of flight booking operations. Developed using Java and Spring Boot, the application demonstrates a scalable architecture suitable for handling user registrations, flight information, bookings, ticketing, and API routing.

🧱 Project Structure
The application is organized into several microservices, each responsible for a specific domain:

ApiGate: Acts as the API Gateway, routing external requests to the appropriate internal services.

BookingApi: Manages booking-related operations, including creating and retrieving bookings.

Config-server: Provides centralized configuration management for all microservices.

FlightApi: Handles flight-related information, such as adding and retrieving flight details.

ServicesResgsitry: Functions as the Eureka Server for service discovery, allowing microservices to locate each other.

TicketApi: Manages ticketing operations, including issuing and retrieving tickets.

UserApi: Handles user registration and authentication processes.

⚙️ Technologies Used
Java 17: Core programming language for developing the application.

Spring Boot: Framework for building stand-alone, production-grade Spring-based applications.

Spring Cloud: Provides tools for building and deploying microservices.

Eureka Server: Service registry for discovering and managing microservices.

Spring Cloud Config Server: Centralized configuration management for distributed systems.

Maven: Build automation and dependency management tool.

REST APIs: Facilitates communication between microservices.
