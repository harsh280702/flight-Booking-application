package com.BookingApplication.BookingApi.client;

import com.BookingApplication.BookingApi.dto.TicketEntityDTO;
import com.BookingApplication.BookingApi.external.TIcketEntityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "TicketApi", url = "http://localhost:8084",path = "/api/ticket")
public interface TicketClient {

    @DeleteMapping("/cancel/{id}")
    TIcketEntityDTO cancelTIcketbyid(@PathVariable("id") Long id);

    @PostMapping("/bookedTicket")
    TIcketEntityDTO bookedTicket(@RequestBody TicketEntityDTO ticket);

    @GetMapping
    List<TicketEntityDTO> getbookedTickets(@PathVariable Long id);

    @GetMapping("/getAllBookedTicket/{flightid}")
    int getBookedTicket(@PathVariable Long flightid);
}
