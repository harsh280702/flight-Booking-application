package com.BookingApplication.TicketApi.services;

import com.BookingApplication.TicketApi.dto.ResponseDTO;
import com.BookingApplication.TicketApi.dto.TicketEntityDTO;
import com.BookingApplication.TicketApi.entity.TicketEntity;

import java.util.List;

public interface ITicketServices {

    ResponseDTO  getTicket();
    ResponseDTO addTicket (TicketEntity ticket);
    ResponseDTO cancelTicket (Long id);
    ResponseDTO getTicketId(Long id);




}
