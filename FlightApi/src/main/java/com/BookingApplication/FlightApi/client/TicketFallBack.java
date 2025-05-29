package com.BookingApplication.FlightApi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class TicketFallBack implements TicketClient {


    @Override
    public int getAllBookedTicketCount(Long flightid) {
        return 0;
    }
}
