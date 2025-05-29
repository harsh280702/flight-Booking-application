package com.BookingApplication.FlightApi.services;

import com.BookingApplication.FlightApi.dto.FlightEntityDTO;
import com.BookingApplication.FlightApi.dto.FlightRatingDTO;
import com.BookingApplication.FlightApi.dto.ResponseDTO;
import com.BookingApplication.FlightApi.entity.FlightEntity;
import org.apache.coyote.Response;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;

public interface IFlightServices {


    ResponseDTO findById(Long id);
    ResponseDTO findByFlightNo(Long flightNo);

    ResponseDTO getTwoWayFligth(String departureCity, String arrivalCity,LocalDate departureDate ,LocalDate arrivalDate);
    ResponseDTO getOneWayFligth(String departureCity, String arrivalCity,LocalDate departureDate );
 ResponseDTO addFlight(FlightEntity flightEntity);
    ResponseDTO updateFlight(Long id ,  FlightEntity flightEntity);
    ResponseDTO deleteFlight(Long id);
    ResponseDTO getAllFlights();
    ResponseDTO findByAirlineName(String airlineName);
    public int calculateAvailableSeats(Long flightId);


    void submitRating(Long flightId,Long userId,Double rating);
    List<FlightRatingDTO> getHighestRating();

}
