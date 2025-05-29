package com.BookingApplication.FlightApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private int statusCode;
     private String message;
     private FlightEntityDTO flightEntityDTO;
     private List<FlightEntityDTO> flightEntityDTOList;
}
