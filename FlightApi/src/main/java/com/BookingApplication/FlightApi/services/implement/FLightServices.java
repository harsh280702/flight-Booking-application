package com.BookingApplication.FlightApi.services.implement;

import com.BookingApplication.FlightApi.client.TicketClient;
import com.BookingApplication.FlightApi.dto.FlightEntityDTO;
import com.BookingApplication.FlightApi.dto.FlightRatingDTO;
import com.BookingApplication.FlightApi.dto.ResponseDTO;
import com.BookingApplication.FlightApi.entity.FlightEntity;
import com.BookingApplication.FlightApi.entity.FlightRating;
import com.BookingApplication.FlightApi.exception.OurException;
import com.BookingApplication.FlightApi.mapper.Utils;
import com.BookingApplication.FlightApi.repostiry.FlightRatingRepostiry;
import com.BookingApplication.FlightApi.repostiry.FlightRepostiry;
import com.BookingApplication.FlightApi.services.IFlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FLightServices  implements IFlightServices {


    private  Long number=230239384L;
    @Autowired
    private FlightRepostiry flightRepostiry;
    @Autowired
    private TicketClient ticketClient;

    @Autowired
    private FlightRatingRepostiry flightRatingRepostiry;




    @Override
    public ResponseDTO findById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{

            FlightEntity flightEntity = flightRepostiry.findById(id).orElseThrow(()-> new OurException("Flight not found"));
            FlightEntityDTO flightEntityDTO = Utils.mapperFlightEntityToFlightEntityDTO(flightEntity);
            responseDTO.setFlightEntityDTO(flightEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");

        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());

        }
        catch(Exception e){

           responseDTO.setStatusCode(500);
          responseDTO.setMessage(e.getMessage());
        }

         return responseDTO;
    }

    @Override
    public ResponseDTO findByFlightNo(Long flightNo) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{

            FlightEntity flightEntity = flightRepostiry.findFlightEntitiesByFlightNumber(flightNo);
            FlightEntityDTO flightEntityDTO = Utils.mapperFlightEntityToFlightEntityDTO(flightEntity);
            responseDTO.setFlightEntityDTO(flightEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");

        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());

        }
        catch(Exception e){

            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO getTwoWayFligth(String departureCity, String arrivalCity, LocalDate departureDate, LocalDate arrivalDate) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{

            List<FlightEntity> flightEntity = flightRepostiry.findFlightsWithInvalidDateAndMatchingDetailsTwoWay(departureCity,arrivalCity,departureDate,arrivalDate);
            List<FlightEntityDTO> flightEntityDTO = Utils.mapFlightListEntityToFlightListDTO(flightEntity);
            responseDTO.setFlightEntityDTOList(flightEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");

        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());

        }
        catch(Exception e){

            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }

        return responseDTO;

    }

    @Override
    public ResponseDTO getOneWayFligth(String departureCity, String arrivalCity, LocalDate departureDate) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{

            List<FlightEntity> flightEntity = flightRepostiry.findFlightsWithInvalidDateAndMatchingDetailsOneWay(departureCity,arrivalCity,departureDate);
            List<FlightEntityDTO> flightEntityDTO = Utils.mapFlightListEntityToFlightListDTO(flightEntity);
            responseDTO.setFlightEntityDTOList(flightEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");

        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
            System.out.println(e.getMessage());

        }
        catch(Exception e){

            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());

            System.out.println(e.getMessage());
        }

        return responseDTO;
    }



    @Override
    public ResponseDTO addFlight(FlightEntity flightEntity) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
                flightEntity.setFlightNumber(number++);
                FlightEntity flightEntity1= flightRepostiry.save(flightEntity);
                FlightEntityDTO flightEntityDTO = Utils.mapperFlightEntityToFlightEntityDTO(flightEntity1);
                responseDTO.setFlightEntityDTO(flightEntityDTO);
                responseDTO.setStatusCode(200);
                responseDTO.setMessage("Success");

        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());

        }
        catch(Exception e){

            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }

        return responseDTO;
    }


    @Override
    public ResponseDTO updateFlight(Long id, FlightEntity flightEntity) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{

            FlightEntity flightEntity1 = flightRepostiry.findById(id).orElseThrow(()-> new OurException("Flight not found"));
            if(flightEntity1!=null){
                flightEntity1= flightRepostiry.save(flightEntity);
                FlightEntityDTO flightEntityDTO = Utils.mapperFlightEntityToFlightEntityDTO(flightEntity1);
                responseDTO.setFlightEntityDTO(flightEntityDTO);
                responseDTO.setStatusCode(200);
                responseDTO.setMessage("Success");
            }
        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());

        }
        catch(Exception e){

            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO deleteFlight(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{

            FlightEntity flightEntity1 = flightRepostiry.findById(id).orElseThrow(()-> new OurException("Flight not found"));
            if(flightEntity1!=null){
                flightRepostiry.deleteById(id);
                responseDTO.setStatusCode(200);
                responseDTO.setMessage("Success");
            }
        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());

        }
        catch(Exception e){

            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO getAllFlights() {
        ResponseDTO responseDTO = new ResponseDTO();
        try{

            List<FlightEntity> flightEntity = flightRepostiry.findAll();
            List<FlightEntityDTO> flightEntityDTO = Utils.mapFlightListEntityToFlightListDTO(flightEntity);
            responseDTO.setFlightEntityDTOList(flightEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");

        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());

        }
        catch(Exception e){

            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO findByAirlineName(String airlineName) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{

            List<FlightEntity> flightEntity = flightRepostiry.findFlightEntitiesByAirlineName(airlineName);
            List<FlightEntityDTO> flightEntityDTO = Utils.mapFlightListEntityToFlightListDTO(flightEntity);
            responseDTO.setFlightEntityDTOList(flightEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");

        }
        catch(OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());

        }
        catch(Exception e){

            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }

        return responseDTO;
    }



    public int calculateAvailableSeats(Long flightId) {
        FlightEntity flight = flightRepostiry.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + flightId));
        int bookedSeats = ticketClient.getAllBookedTicketCount(flightId);
        return flight.getTotalSeats() - bookedSeats;
    }

    @Override
    public void submitRating(Long flightId, Long userId, Double rating) {

        FlightEntity flight = flightRepostiry.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        FlightRating flightRating = FlightRating.builder()
                .flight(flight)
                .userId(userId)
                .ratingValue(rating)
                .build();

        flightRatingRepostiry.save(flightRating);


    }

    @Override
    public List<FlightRatingDTO> getHighestRating() {

        List<FlightEntity> flights = flightRepostiry.findAll();
        List<FlightRatingDTO> result = new ArrayList<>();

        for (FlightEntity flight : flights) {
            List<FlightRating> ratings = flightRatingRepostiry.findByFlight(flight);
            double maxRating = ratings.stream()
                    .mapToDouble(FlightRating::getRatingValue)
                    .max()
                    .orElse(0.0);

            result.add(new FlightRatingDTO(
                    flight.getId(),
                    flight.getFlightNumber(),
                    flight.getDepartureCity(),
                    flight.getArrivalCity(),
                    maxRating
            ));
        }

        return result;
    }

    public List<Map<String, Object>> getFlightsWithHighestAverageRatings() {
        List<Map<String, Object>> rawRatings = flightRatingRepostiry.findFlightRatingsOrderedByAverage();

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> entry : rawRatings) {
            Long flightId = (Long) entry.get("flightId");
            Double avgRating = (Double) entry.get("avgRating");
            FlightEntity flight = flightRepostiry.findById(flightId).orElse(null);
            if (flight != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("flightId", flight.getId());
                data.put("flightNumber", flight.getFlightNumber());
                data.put("airline", flight.getAirlineName());
                data.put("origin", flight.getDepartureCity());
                data.put("destination", flight.getArrivalCity());
                data.put("averageRating", avgRating);
                result.add(data);
            }
        }
        return result;
    }

    // the code is End Here
}
