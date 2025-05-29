package com.BookingApplication.TicketApi.controller;


import com.BookingApplication.TicketApi.dto.ResponseDTO;
import com.BookingApplication.TicketApi.dto.TicketEntityDTO;
import com.BookingApplication.TicketApi.entity.TicketEntity;
import com.BookingApplication.TicketApi.repostiry.TicketRepostiry;
import com.BookingApplication.TicketApi.services.ITicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {


    @Autowired
    private ITicketServices ticketServices;


    @Autowired
    private TicketRepostiry ticketRepostiry;

    @GetMapping("/all")
    public ResponseEntity<List<TicketEntityDTO>> getAllTickets() {
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO = ticketServices.getTicket();
        if (responseDTO == null) {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("No Tickets Found");
        }

        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getTicketEntityDTOList());
    }



    @PostMapping("/bookedTicket")
    public ResponseEntity<List<TicketEntityDTO>> bookedticket(@RequestBody TicketEntity ticket) {
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO = ticketServices.addTicket(ticket);
        if (responseDTO == null) {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("No Tickets Found");
        }

        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getTicketEntityDTOList());
    }
    @DeleteMapping("/cancelticket/{id}")
    public ResponseEntity<List<TicketEntityDTO>> CancelTicket(@PathVariable("id") Long id) {
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO = ticketServices.cancelTicket(id);
        if (responseDTO == null) {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("No Tickets Found");
        }

        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getTicketEntityDTOList());
    }


    @GetMapping("/get-ticket-By-id/{id}")
    public ResponseEntity<TicketEntityDTO> getTicketByid( @PathVariable("id") Long   id  ) {
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO = ticketServices.getTicketId(id);
        if (responseDTO == null) {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("No Tickets Found");
        }

        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getTicketEntityDTO());
    }



    @GetMapping("/getAllBookedTicket/{flightid}")
    public int getBookedTicketCount(@PathVariable("flightid") Long flightid) {
        return ticketRepostiry.countByFlightId(flightid);
    }

}
