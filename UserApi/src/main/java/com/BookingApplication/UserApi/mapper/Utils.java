package com.BookingApplication.UserApi.mapper;

import com.BookingApplication.UserApi.dto.UserEntityDTO;

import com.BookingApplication.UserApi.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {



    public static UserEntityDTO mapperUserEntityToUserEntityDTO(UserEntity userEntity) {

        UserEntityDTO userEntityDTO = new UserEntityDTO();
        userEntityDTO.setId(userEntity.getId());
        userEntityDTO.setFirstName(userEntity.getFirstName());
       // userEntityDTO.setLastName(userEntity.getLastName());
        userEntityDTO.setEmail(userEntity.getEmail());
        userEntityDTO.setPassword(userEntity.getPassword());
        userEntityDTO.setRole(userEntity.getRole());
//        userEntityDTO.setPhone(userEntity.getPhone());
//        userEntityDTO.setPassPortId(userEntity.getPassPortId());
//        userEntityDTO.setZip(userEntity.getZip());
//        userEntityDTO.setCity(userEntity.getCity());
//        userEntityDTO.setAddress(userEntity.getAddress());
//        userEntityDTO.setBookingId(userEntity.getBookingId());
//        userEntityDTO.setFlightId(userEntity.getFlightId());  
        userEntityDTO.setUserName(userEntity.getUserName());
        userEntityDTO.setKeycloakId(userEntity.getKeycloakId());
        return userEntityDTO;

    }

    public static List<UserEntityDTO> mapUserListEntityToUserListDTO(List<UserEntity> userList){
        return userList.stream().map(Utils::mapperUserEntityToUserEntityDTO).collect(Collectors.toList());
    }





}
