package com.BookingApplication.FlightApi.controller;


import com.BookingApplication.FlightApi.dto.FlightEntityDTO;
import com.BookingApplication.FlightApi.dto.FlightRatingDTO;
import com.BookingApplication.FlightApi.dto.ResponseDTO;
import com.BookingApplication.FlightApi.entity.FlightEntity;
import com.BookingApplication.FlightApi.entity.FlightRating;
import com.BookingApplication.FlightApi.services.IFlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {


    @Autowired
    private IFlightServices flightServices;


    @GetMapping("/get-flight-by-airlineName/{airlineName}")
    public ResponseEntity<List<FlightEntityDTO>> getFlightByAirlineName(@PathVariable("airlineName") String airlineName) {

        ResponseDTO responseDTO = flightServices.findByAirlineName(airlineName);

        if (responseDTO == null) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("flight not found this airline name");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTOList());
    }




    @GetMapping("/get-flight-by-twoWay/{departureCity}/{arrivalCity}/{departureDate}/{arrivaldate}")
    public ResponseEntity<List<FlightEntityDTO>> getTwoWayFlight(
            @PathVariable("departureCity") String departureCity
            , @PathVariable("arrivalCity") String arrivalCity
            , @PathVariable("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @PathVariable("arrivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ArrivalDate
    )
    {
        ResponseDTO responseDTO = flightServices.getTwoWayFligth(departureCity,arrivalCity,departureDate,ArrivalDate);

        if (responseDTO == null) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("this is no flight in between departure city And arrival city");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTOList());
    }



    @GetMapping("/get-flight-by-oneWay/{departureCity}/{arrivalCity}/{departureDate}")
    public ResponseEntity<List<FlightEntityDTO>> getOneWayFlight(
            @PathVariable("departureCity") String departureCity
            , @PathVariable("arrivalCity") String arrivalCity
            , @PathVariable("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate
    )
    {
        ResponseDTO responseDTO = flightServices.getOneWayFligth(departureCity,arrivalCity,departureDate);

        if (responseDTO == null) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("this is no flight in between departure city And arrival city");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTOList());
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightEntityDTO>> getAllFlights() {
        ResponseDTO responseDTO = flightServices.getAllFlights();
        if (responseDTO == null) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("flights not found");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTOList());
    }

    @GetMapping("/get-flight-By-id/{id}")
    public ResponseEntity<FlightEntityDTO> getFlightById(@PathVariable("id")  Long id) {
        ResponseDTO responseDTO = flightServices.findById(id);
        if(responseDTO == null){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("flight not found");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTO());
    }

    @GetMapping("/get-flight-by-flightNo")
    public ResponseEntity<FlightEntityDTO> getFlightByFlightNo(
            @PathVariable("flightNo") Long flghtNo) {

        ResponseDTO responseDTO = flightServices.findByFlightNo(flghtNo);
        if (responseDTO == null) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("flight not found");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTO());
    }
    @PostMapping("/add")
    public ResponseEntity<FlightEntityDTO> addFlight(@RequestBody FlightEntity flightEntity) {
        ResponseDTO responseDTO = flightServices.addFlight(flightEntity);
        if (responseDTO == null) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("flight not added");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTO());
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<FlightEntityDTO> cancelFlight(@PathVariable Long id) {

        ResponseDTO responseDTO = flightServices.deleteFlight(id);
        if (responseDTO == null) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("flight not found");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTO());
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<FlightEntityDTO> updateFlight(@PathVariable Long id, @RequestBody FlightEntity flightEntity) {
        ResponseDTO responseDTO = flightServices.updateFlight(id, flightEntity);
        if (responseDTO == null) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("flight not updated");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getFlightEntityDTO());
    }

    @GetMapping("/available-Seats/{flightid}")
    public ResponseEntity<Integer> getAvailableSeats( @PathVariable("flightid") Long flightId) {

        int availableSeats = flightServices.calculateAvailableSeats(flightId);
        if (availableSeats == 0) {
            return ResponseEntity.status(400).body(-1);
        }
        return ResponseEntity.status(200).body(availableSeats);
    }




    @PostMapping("/rating/{flightid}/{userid}/{ratting}")
    public ResponseEntity <String> submitRating(@PathVariable("flightid") Long flightId ,@PathVariable ("userid") Long userId,@PathVariable("ratting") Double rating) {
        if(rating == null && flightId == null && userId == null){
            return ResponseEntity.status(400).body("  please enter the rating, FLightid and User Id properly");
        }
        flightServices.submitRating(flightId, userId, rating);
        return ResponseEntity.status(200).body("Rating submitted");
    }


    @GetMapping("/highest-ratings")
    public ResponseEntity<List<FlightRatingDTO>> getFlightsWithHighestRatings() {
       List< FlightRatingDTO> flightRatingDTO =  flightServices.getHighestRating();
       if (flightRatingDTO == null) {
           return ResponseEntity.status(400).body(null);
       }
       return ResponseEntity.status(200).body(flightRatingDTO);
    }










}
