package com.BookingApplication.BookingApi.external;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FlightEntityDTO {

    private Long flightId;
    private Long flightNo;
    private String arilineName;
    private String flightType;
    private String flightTime;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String departureCity;
    private String arrivalCity;
    private String price;

    private Double fare;
}
