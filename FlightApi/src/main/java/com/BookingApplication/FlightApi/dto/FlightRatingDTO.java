package com.BookingApplication.FlightApi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightRatingDTO {
    private Long flightId;
    private Long flightNumber;
    private String departureCity;
    private String arrivalCity;
    private Double highestRating;

}
