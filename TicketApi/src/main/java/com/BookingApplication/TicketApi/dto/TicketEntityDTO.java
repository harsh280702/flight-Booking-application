package com.BookingApplication.TicketApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketEntityDTO {
    private Long ticketId;
    private Long bookingId;
    private Long flightId;
    private Long userId;
    private  String flightName;
    private String passengerName;
    private  int passengerAge;
    private String passengerGender;
    private String seatNumber;

    private LocalDateTime boardingTime;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private String ticketStatus;
    private Double fare;

    private String notificationStatus;
    private Boolean emailSent;
    private Boolean smsSent;
    private LocalDateTime createdAt;
}


//
//@Override
//public Ticket bookTicket(TicketRequest req) {
//    // Save ticket
//    Ticket ticket = new Ticket();
//    ticket.setTicketNumber("TKT-" + UUID.randomUUID().toString().substring(0, 8));
//    ticket.setBookingId(req.getBookingId());
//    ticket.setPassengerName(req.getPassengerName());
//    ticket.setPassengerAge(req.getPassengerAge());
//    ticket.setPassengerGender(req.getPassengerGender());
//    ticket.setFlightId(req.getFlightId());
//    ticket.setSeatNumber(req.getSeatNumber());
//    ticket.setPrice(req.getPrice());
//    ticket.setBoardingTime(req.getBoardingTime());
//    ticket.setTicketStatus("CONFIRMED");
//    ticket.setIssuedAt(LocalDateTime.now());
//
//    Ticket saved = ticketRepository.save(ticket);
//
//    // Get booking/user info
//    BookingDTO booking = bookingClient.getBooking(req.getBookingId());
//    FlightDTO flight = flightClient.getFlight(req.getFlightId());
//
//    // Send notifications
//    String message = "Dear " + ticket.getPassengerName() + ",\nYour ticket for flight " +
//            flight.getFlightNumber() + " is confirmed. Seat: " + ticket.getSeatNumber();
//
//    notificationService.sendTicketEmail(booking.getUser().getEmail(), message);
//    notificationService.sendTicketSMS(booking.getUser().getPhone(), message);
//
//    return saved;
//}