package com.BookingApplication.BookingApi.controller;


import com.BookingApplication.BookingApi.client.TicketClient;
import com.BookingApplication.BookingApi.client.UserClient;
import com.BookingApplication.BookingApi.dto.BookingEntityDTO;
import com.BookingApplication.BookingApi.dto.PassengerDTO;
import com.BookingApplication.BookingApi.dto.ResponseDTO;
import com.BookingApplication.BookingApi.dto.TicketEntityDTO;
import com.BookingApplication.BookingApi.entity.BookingEntity;
import com.BookingApplication.BookingApi.entity.Passenger;
import com.BookingApplication.BookingApi.external.UserEntityDTO;
import com.BookingApplication.BookingApi.services.IBookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookig")
public class BookingController {



    @Autowired
    private IBookingServices bookingServices;
    @Autowired
     private UserClient userClient;
    @Autowired
    private TicketClient ticketClient;
     private   JavaMailSender mailSender;

    @GetMapping("/get-booking-by-flight-id/{flightid}")
    public ResponseEntity<List<BookingEntityDTO>> getAllBookingByFlightId(@PathVariable("flightid") Long flightId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO=bookingServices.getAllBookingsByFlighId(flightId);
        if(responseDTO == null){
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("No Booking found for this  flight  " + flightId);
        }
        return  ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getBookingEntityDTOList());
    }

    @GetMapping("get-booking-by-flight-dapeartureDate-and-arrivalDate/{departureDate}/{arrivalDate}")
    public  ResponseEntity<List<BookingEntityDTO>> getAllBookingByflightDepartureDateAndArrivalDate (
            @PathVariable("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @PathVariable("arrivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)     LocalDate arrivalDate
    ){
        ResponseDTO responseDTO = new ResponseDTO();
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getBookingEntityDTOList());
    }



    @PostMapping("/booked-ticket/{userid}/{flightid}")
    public ResponseEntity<BookingEntityDTO> BookedTicket(@PathVariable("userid") Long userid,
                                                         @PathVariable("flightId") Long flightid,
                                                         @RequestBody BookingEntity bookingEntity,
                                                         @RequestBody Passenger passenger  ){
        ResponseDTO responseDTO = bookingServices.createBooking(userid,flightid,bookingEntity,passenger);
        if(responseDTO == null){
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("No Booking found for this  flight  " + flightid);
        }


        SendNotificationToUserForBooking(bookingEntity);

        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getBookingEntityDTO());

    }
    private void  SendNotificationToUserForBooking( BookingEntity bookingEntity) {

        SimpleMailMessage message = new SimpleMailMessage();
        UserEntityDTO userEntityDTO = userClient.getUserById(bookingEntity.getUserId());
        List<TicketEntityDTO> ticketEntityDTO = ticketClient.getbookedTickets(userEntityDTO.getId());

        message.setTo(userEntityDTO.getEmail());

        message.setSubject("Welcome to Our App");
        message.setText("Hi " + userEntityDTO.getUserName()+ ", your booking is Seccussefully" +bookingEntity.getBookingStatus()+
         "and has been booked for the flight " + bookingEntity.getFlightId()+"and your ticket number is  "+ bookingEntity.getTicketId()
        +"your seat number is"+ ticketEntityDTO );
        mailSender.send(message);
    }

    @DeleteMapping("cancelTicket/{bookingId}")
    public ResponseEntity<BookingEntityDTO> cancelTicket(@PathVariable("bookingId") Long bookingId){
        ResponseDTO responseDTO = bookingServices.cancancelBooking(bookingId);
        if(responseDTO == null){
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("No Booking found ");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getBookingEntityDTO());
    }



    @GetMapping("/getbooking-user-by-id/{userid}")
    public ResponseEntity<List<BookingEntityDTO>> getAllBookingByUser(@PathVariable("userid") Long userid){
        ResponseDTO responseDTO =  bookingServices.getAllBookingsByUserId(userid);
        if(responseDTO == null){
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("No Booking found ");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getBookingEntityDTOList());
    }







}
