package com.BookingApplication.BookingApi.services.implement;

import com.BookingApplication.BookingApi.client.FLightClient;
import com.BookingApplication.BookingApi.client.TicketClient;
import com.BookingApplication.BookingApi.client.UserClient;
import com.BookingApplication.BookingApi.dto.BookingEntityDTO;
import com.BookingApplication.BookingApi.dto.PassengerDTO;
import com.BookingApplication.BookingApi.dto.ResponseDTO;
import com.BookingApplication.BookingApi.dto.TicketEntityDTO;
import com.BookingApplication.BookingApi.entity.BookingEntity;
import com.BookingApplication.BookingApi.entity.Passenger;
import com.BookingApplication.BookingApi.exception.OurException;
import com.BookingApplication.BookingApi.external.FlightEntityDTO;
import com.BookingApplication.BookingApi.external.UserEntityDTO;
import com.BookingApplication.BookingApi.mapper.Utils;
import com.BookingApplication.BookingApi.repostiry.BookingRepostiry;
import com.BookingApplication.BookingApi.services.IBookingServices;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BookingServices implements IBookingServices {


    @Autowired
    private BookingRepostiry bookingRepostiry;

    @Autowired
    private FLightClient flightClient;


    @Autowired
     private UserClient userClient;
    @Autowired
    private TicketClient ticketClient;




    @Override
    public ResponseDTO getAllBookingsByFlighId(Long flighId) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
           List< BookingEntity>  bookingEntity = bookingRepostiry.findBookingEntitiesByFlightId(flighId);
           List <BookingEntityDTO> bookingEntityDTO = Utils.mappBookingEntityListToBookingDTOList(bookingEntity);
            responseDTO.setBookingEntityDTOList(bookingEntityDTO);
            responseDTO.setStatusCode(200);
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getAllBookingsByUserId(Long userId) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List< BookingEntity>  bookingEntity = bookingRepostiry.getBookingEntitiesByUserId(userId);
            List <BookingEntityDTO> bookingEntityDTO = Utils.mappBookingEntityListToBookingDTOList(bookingEntity);
            responseDTO.setBookingEntityDTOList(bookingEntityDTO);
            responseDTO.setStatusCode(200);
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getAllBookings() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
          List<BookingEntity> bookingEntity = bookingRepostiry.findAll();
            List<BookingEntityDTO> bookingEntityDTO = Utils.mappBookingEntityListToBookingDTOList(bookingEntity);
            responseDTO.setBookingEntityDTOList(bookingEntityDTO);
            responseDTO.setStatusCode(200);
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getBookingById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            BookingEntity  bookingEntity = bookingRepostiry.findById(id).orElseThrow(() -> new OurException("Booking not found"));
            BookingEntityDTO bookingEntityDTO = Utils.mappedBookingEntityToBookingDTO(bookingEntity);
            responseDTO.setMessage("Booking cancelled");
            responseDTO.setBookingEntityDTO(bookingEntityDTO);
            responseDTO.setStatusCode(200);
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO createBooking(Long userId, Long flihgtId, BookingEntity booking, Passenger passenger ) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {


            FlightEntityDTO flight = flightClient.getFlightById(flihgtId);
            UserEntityDTO user = userClient.getUserById(userId);

            if (flight.getDepartureDate().isAfter(flight.getArrivalDate())) {
                throw new IllegalArgumentException("Departure date cannot be after arrival date");
            }

            if (booking.getNumberOfPassengers()<ticketClient.getBookedTicket(flight.getFlightId()))
            {
                throw new IllegalArgumentException("Number of passengers exceeds ticket limit");
            }

            // set  the booking
            booking.setFlightId(flight.getFlightId());
            booking.setUserId(user.getId());
            booking.setCreatedAt(LocalDateTime.now());
            booking.setTotalFare(flight.getFare()*booking.getNumberOfPassengers());
            booking.setBookingConfirmationCode(UUID.randomUUID().toString());
            booking.setBookingStatus("CONFIRMED");
          BookingEntity savedBooking =  bookingRepostiry.save(booking);
          BookingEntityDTO bookingEntityDTO= Utils.mappedBookingEntityToBookingDTO(booking);


            // Step 4: Book tickets (loop for count)
            for (int i = 0; i < booking.getNumberOfPassengers() ; i++) {
                TicketEntityDTO ticket = new TicketEntityDTO();
                ticket.setBookingId(savedBooking.getBookingId());
                ticket.setFlightId(flight.getFlightId());
                ticket.setUserId(user.getId());
                ticket.setPassengerName(passenger.getName() + " #" + (i + 1));
                ticket.setSeatNumber("S" + (100 + i)); // Dynamic seat number
                ticket.setBoardingTime(LocalDateTime.from(flight.getDepartureTime()));
                ticket.setDepartureTime(LocalDateTime.from(flight.getDepartureTime()));
                ticket.setArrivalTime(LocalDateTime.from(flight.getArrivalTime()));
                ticket.setTicketStatus("BOOKED");
                ticket.setFare(flight.getFare());
                ticket.setNotificationStatus("PENDING");
                ticket.setEmailSent(true);
                ticket.setSmsSent(false);
                ticketClient.bookedTicket(ticket);
            }

            responseDTO.setBookingEntityDTO(bookingEntityDTO);
            responseDTO.setStatusCode(200);
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;



    }

    @Override
    public ResponseDTO cancancelBooking(Long bookingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {

            BookingEntity  bookingEntity  = bookingRepostiry.findById(bookingId).orElseThrow(() -> new OurException("Booking not found"));
            bookingRepostiry.deleteById(bookingId);
            ticketClient.cancelTIcketbyid(bookingId);
            responseDTO.setMessage("Booking cancelled");
            responseDTO.setStatusCode(200);
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }
}
