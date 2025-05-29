package com.BookingApplication.TicketApi.dto;

import lombok.Data;

import java.util.List;
@Data
public class ResponseDTO {

    private  int statusCode;
    private String message;
    private TicketEntityDTO  ticketEntityDTO;
    private List<TicketEntityDTO> ticketEntityDTOList;
}
