package com.BookingApplication.UserApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntityDTO {
    private Long id;
    private String firstName;
//    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String role;

    private  String keycloakId;
//    private String passPortId;
//    private String phone;
//    private String address;
//    private String city;
//    private String state;
//    private String zip;
//    private Long bookingId;
//    private Long flightId;

}
