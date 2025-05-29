package com.BookingApplication.TicketApi.mapper;

import com.BookingApplication.TicketApi.dto.TicketEntityDTO;
import com.BookingApplication.TicketApi.entity.TicketEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {



    public static TicketEntityDTO mapTicketEntityToTicketEntityDTO(TicketEntity ticketEntity){
        TicketEntityDTO ticketEntityDTO = new TicketEntityDTO();
        ticketEntityDTO.setTicketId(ticketEntity.getTicketId());
        ticketEntityDTO.setBookingId(ticketEntity.getBookingId());
        ticketEntityDTO.setFlightId(ticketEntity.getFlightId());
        ticketEntityDTO.setUserId( ticketEntity.getUserId());
        ticketEntityDTO.setFlightName(ticketEntity.getFlightName());
        ticketEntityDTO.setPassengerName(ticketEntity.getPassengerName());
        ticketEntityDTO.setSeatNumber(ticketEntity.getSeatNumber());
        ticketEntityDTO.setTicketStatus(ticketEntity.getTicketStatus());
        ticketEntityDTO.setArrivalTime(ticketEntity.getArrivalTime());
        ticketEntityDTO.setDepartureTime(ticketEntity.getDepartureTime());
        ticketEntityDTO.setSmsSent(ticketEntity.getSmsSent());
        ticketEntityDTO.setEmailSent(ticketEntity.getEmailSent());
        ticketEntityDTO.setCreatedAt(ticketEntity.getCreatedAt());
        ticketEntityDTO.setNotificationStatus(ticketEntity.getNotificationStatus());
        return ticketEntityDTO;
    }



    public static List<TicketEntityDTO> mapUserListEntityToUserListDTO(List<TicketEntity> ticketEntities){
        return ticketEntities.stream().map(Utils::mapTicketEntityToTicketEntityDTO).collect(Collectors.toList());
    }
}
