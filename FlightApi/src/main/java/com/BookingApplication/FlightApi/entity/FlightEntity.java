package com.BookingApplication.FlightApi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     private Long flightNumber;
     private String airlineName;
     private String flightType;
     private String flightTime;
     private LocalDate departureDate;
     private LocalDate arrivalDate;
     private LocalTime departureTime;
     private LocalTime arrivalTime;
     private String departureCity;
     private String arrivalCity;
     private Double price;
     private int  totalSeats;
     private Double averageRating ;

}
