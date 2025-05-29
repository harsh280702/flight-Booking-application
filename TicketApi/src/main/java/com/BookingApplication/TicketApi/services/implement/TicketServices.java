package com.BookingApplication.TicketApi.services.implement;


import com.BookingApplication.TicketApi.dto.ResponseDTO;
import com.BookingApplication.TicketApi.dto.TicketEntityDTO;
import com.BookingApplication.TicketApi.entity.TicketEntity;
import com.BookingApplication.TicketApi.exception.OurException;
import com.BookingApplication.TicketApi.mapper.Utils;
import com.BookingApplication.TicketApi.repostiry.TicketRepostiry;
import com.BookingApplication.TicketApi.services.ITicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServices implements ITicketServices {

    @Autowired
    private TicketRepostiry ticketRepostiry;



    @Override
    public ResponseDTO getTicket() {

        ResponseDTO responseDTO = new ResponseDTO();

        try{
            List<TicketEntity> ticketEntity = ticketRepostiry.findAll();
            List<TicketEntityDTO> ticketEntityDTOList = Utils.mapUserListEntityToUserListDTO(ticketEntity);
            responseDTO.setTicketEntityDTOList(ticketEntityDTOList);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e){
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return  responseDTO;
    }


    @Override
    public ResponseDTO addTicket(TicketEntity ticket) {

        ResponseDTO responseDTO = new ResponseDTO();

        try{
            TicketEntity ticketEntity = ticketRepostiry.save(ticket);
            TicketEntityDTO ticketEntityDTO  = Utils.mapTicketEntityToTicketEntityDTO(ticketEntity);
            responseDTO.setTicketEntityDTO(ticketEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e){
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return  responseDTO;
    }

    @Override
    public  ResponseDTO cancelTicket(Long id) {
ResponseDTO responseDTO = new ResponseDTO();
        try{
            TicketEntity ticketEntity = ticketRepostiry.findById(id).orElseThrow(() -> new OurException("Ticket not found"));
        if(ticketEntity!=null) {
            ticketRepostiry.delete(ticketEntity);
        }
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e){
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return  responseDTO;
    }

    @Override
    public ResponseDTO getTicketId(Long id) {

        ResponseDTO responseDTO = new ResponseDTO();
        try{
            List<TicketEntity> ticketEntity = ticketRepostiry.findByUserId(id);
            List<TicketEntityDTO> ticketEntityDTO = Utils.mapUserListEntityToUserListDTO(ticketEntity);
            responseDTO.setTicketEntityDTOList(ticketEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Success");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e){
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return  responseDTO;
    }



    // this code END here
}
