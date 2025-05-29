package com.BookingApplication.FlightApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FlightEntityDTO {

    private Long id;
    private Long flightNo;
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


}
