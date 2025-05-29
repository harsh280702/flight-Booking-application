package com.BookingApplication.BookingApi.dto;

import com.BookingApplication.BookingApi.entity.BookingEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PassengerDTO {
    private Long id;
    private Long userId;             // FK to the user
    private String name;
    private int age;
    private String gender;
}
