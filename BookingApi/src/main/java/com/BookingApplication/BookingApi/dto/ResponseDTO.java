package com.BookingApplication.BookingApi.dto;

import com.BookingApplication.BookingApi.entity.BookingEntity;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import org.springframework.boot.context.event.SpringApplicationEvent;

import java.util.List;
@Data
public class ResponseDTO {


    private int statusCode;
    private String message;
    private BookingEntityDTO bookingEntityDTO;
    private List<BookingEntityDTO> bookingEntityDTOList;
}
