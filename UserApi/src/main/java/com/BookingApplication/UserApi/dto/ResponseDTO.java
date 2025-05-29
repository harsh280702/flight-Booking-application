package com.BookingApplication.UserApi.dto;

import com.BookingApplication.UserApi.external.BookingEntityDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {

    private  int statusCode;
    private String message;
    private  UserEntityDTO userEntityDTO;
    private List<UserEntityDTO> userEntityDTOList;
    private List<BookingEntityDTO> bookingEntityDTOList;
}
