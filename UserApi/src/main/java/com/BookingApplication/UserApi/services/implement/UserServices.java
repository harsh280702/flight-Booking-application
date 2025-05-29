package com.BookingApplication.UserApi.services.implement;


import com.BookingApplication.UserApi.client.BookingClient;
import com.BookingApplication.UserApi.dto.ResponseDTO;
import com.BookingApplication.UserApi.dto.UserEntityDTO;

import com.BookingApplication.UserApi.entity.UserEntity;
import com.BookingApplication.UserApi.exception.OurException;
import com.BookingApplication.UserApi.external.BookingEntityDTO;
import com.BookingApplication.UserApi.mapper.Utils;
import com.BookingApplication.UserApi.repostiry.UserRepostiry;
import com.BookingApplication.UserApi.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices  implements IUserServices {

    @Autowired
    private UserRepostiry userRepostiry;

    @Autowired
    private BookingClient client;

    @Override
    public ResponseDTO registerUser(UserEntity userEntity) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            if(userRepostiry.existsByEmail(userEntity.getEmail())){
                UserEntity exitUser = userRepostiry.findByEmail(userEntity.getEmail());
                UserEntityDTO exitUserDTO = new UserEntityDTO();
                exitUserDTO.setEmail(exitUser.getEmail());
                exitUserDTO.setUserName(exitUser.getUserName());
                exitUserDTO.setFirstName(exitUser.getFirstName());
                exitUserDTO.setKeycloakId(exitUser.getKeycloakId());
                exitUserDTO.setPassword(exitUser.getPassword());
                exitUserDTO.setRole(exitUser.getRole());
            }
            if(userEntity.getRole()==null){
                userEntity.setRole("ROLE_USER");
            }

            userEntity.setUserName(userEntity.getEmail());
            UserEntity userEntity1 = userRepostiry.save(userEntity);
            UserEntityDTO userEntityDTO = Utils.mapperUserEntityToUserEntityDTO(userEntity1);
            responseDTO.setUserEntityDTO(userEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("User registered successfully");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getUserByUserName(String userName) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {

            UserEntity userEntity1 = userRepostiry.findUserEntityByUserName(userName);
            if (userEntity1 == null) {
                responseDTO.setStatusCode(400);
                responseDTO.setMessage("User not found");
            }
            UserEntityDTO userEntityDTO = Utils.mapperUserEntityToUserEntityDTO(userEntity1);
            responseDTO.setUserEntityDTO(userEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("User registered successfully");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getUserById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {

            UserEntity userEntity1 = userRepostiry.findById(id).orElse(null);
            if (userEntity1 == null) {
                responseDTO.setStatusCode(400);
                responseDTO.setMessage("User not found");
            }
            UserEntityDTO userEntityDTO = Utils.mapperUserEntityToUserEntityDTO(userEntity1);
            responseDTO.setUserEntityDTO(userEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("User registered successfully");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO updateUser(String role, Long id, UserEntity userEntity) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {

            UserEntity userEntity1 = userRepostiry.findById(id).orElseThrow(() -> new OurException("User not found"));
            if("ADMIN".equals(role)){
               UserEntity  userEntity2 = userRepostiry.save(userEntity);
                UserEntityDTO userEntityDTO = Utils.mapperUserEntityToUserEntityDTO(userEntity2);
                responseDTO.setUserEntityDTO(userEntityDTO);
                responseDTO.setStatusCode(200);
                responseDTO.setMessage("sceesuser found");
            }

            if (userEntity1 == null) {
                responseDTO.setStatusCode(400);
                responseDTO.setMessage("User not found");
            }

            responseDTO.setStatusCode(200);
            responseDTO.setMessage("User registered successfully");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO DeleteUser(String role, Long Id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {

            UserEntity userEntity1 = userRepostiry.findById(Id).orElseThrow(() -> new OurException("User not found"));
            if("ADMIN".equals(role)){
                userRepostiry.deleteById(Id);
                responseDTO.setStatusCode(200);
                responseDTO.setMessage("sceesuser found");
            }

            if (userEntity1 == null) {
                responseDTO.setStatusCode(400);
                responseDTO.setMessage("User not found");
            }

            responseDTO.setStatusCode(200);
            responseDTO.setMessage("User registered successfully");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }


    @Override
    public ResponseDTO getAllUsers() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {

           List<UserEntity> userEntity1 = userRepostiry.findAll();

            if (userEntity1 == null) {
                responseDTO.setStatusCode(400);
                responseDTO.setMessage("User not found");
            }
            List<UserEntityDTO> userEntityDTO = Utils.mapUserListEntityToUserListDTO(userEntity1);
            responseDTO.setUserEntityDTOList(userEntityDTO);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("User registered successfully");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }



    @Override
    public ResponseDTO getUserBookingsByUserId(Long userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            UserEntity user = userRepostiry.findById(userId).orElseThrow(() -> new OurException("User not found"));
            if (user == null) {
                responseDTO.setStatusCode(400);
                responseDTO.setMessage("User not found");
                System.out.println("User not found");
            }
            List<BookingEntityDTO> bookingEntityDTOS = client.getBookingByUserid(user.getId());
            if (bookingEntityDTOS == null) {
                responseDTO.setStatusCode(400);
                responseDTO.setMessage("Bookings not found");
                System.out.println("Bookings not found");
            }

            responseDTO.setBookingEntityDTOList(bookingEntityDTOS);
            responseDTO.setStatusCode(200);
            responseDTO.setMessage(" All Bookings found");
        }
        catch (OurException e){
            responseDTO.setStatusCode(400);
            responseDTO.setMessage(e.getMessage());
            System.out.println(e.getMessage()           );
        }
        catch (Exception e) {
            responseDTO.setStatusCode(500);
            responseDTO.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responseDTO;
    }
}
