package com.BookingApplication.FlightApi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="TicketApi")
public interface TicketClient {


    @GetMapping("/getAllBookedTicket/{flightid}")
    int getAllBookedTicketCount(@PathVariable("flightid") Long flightid);
}
