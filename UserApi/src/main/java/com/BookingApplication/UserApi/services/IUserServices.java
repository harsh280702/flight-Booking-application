package com.BookingApplication.UserApi.services;

import com.BookingApplication.UserApi.dto.ResponseDTO;
import com.BookingApplication.UserApi.entity.UserEntity;

public interface IUserServices {

    ResponseDTO registerUser(UserEntity userEntity);
    ResponseDTO getUserByUserName(String userName);
    ResponseDTO getUserById(Long id);
    ResponseDTO updateUser( String role,Long id,UserEntity userEntity);
    ResponseDTO DeleteUser(String role, Long Id);
    ResponseDTO getAllUsers();
    ResponseDTO getUserBookingsByUserId (Long Id);

}
