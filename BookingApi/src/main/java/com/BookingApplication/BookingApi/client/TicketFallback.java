package com.BookingApplication.BookingApi.client;

import com.BookingApplication.BookingApi.dto.TicketEntityDTO;
import com.BookingApplication.BookingApi.external.TIcketEntityDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketFallback implements TicketClient{

    @Override
    public TIcketEntityDTO cancelTIcketbyid(Long id) {
        return null;
    }

    @Override
    public TIcketEntityDTO bookedTicket(TicketEntityDTO ticket) {
        return null;
    }

    @Override
    public List<TicketEntityDTO> getbookedTickets(Long id) {
        return List.of();
    }

    @Override
    public int getBookedTicket(Long flightid) {
        return 0;
    }
}
