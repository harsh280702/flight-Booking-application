package com.BookingApplication.UserApi.controller;

import com.BookingApplication.UserApi.dto.ResponseDTO;
import com.BookingApplication.UserApi.dto.UserEntityDTO;
import com.BookingApplication.UserApi.entity.UserEntity;
import com.BookingApplication.UserApi.external.BookingEntityDTO;
import com.BookingApplication.UserApi.services.IUserServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import reactor.util.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserServices userServices;


    @Autowired
    private JavaMailSender mailSender;

    private Logger logger;

    //  this is code help to get user by id from database
    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<UserEntityDTO> getUserById(@PathVariable("id") Long id){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO= userServices.getUserById(id
        );

        responseDTO.setStatusCode(200);
        responseDTO.setMessage("User found");
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getUserEntityDTO());
    }



    // this code to help to register the user  in the databases ;
    @PostMapping("/register")
     ResponseEntity<UserEntityDTO> registerUser( @Valid @RequestBody  UserEntity userEntity){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO= userServices.registerUser(userEntity);
       sendNotificationtoUser(userEntity);
       return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getUserEntityDTO());
    }


    // this code help to sent  email notification to user  register successfully
    private void sendNotificationtoUser( UserEntity userEntity) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo( userEntity.getEmail());

        message.setSubject("Welcome to Our App");
        message.setText("Hi " + userEntity.getUserName()+ ", your registration is successful!" +
                " your email is "+userEntity.getEmail()+" Your password is "+userEntity.getPassword());
        mailSender.send(message);

        // ✅ Simulate SMS
        System.out.println("SMS to " + userEntity.getUserName() + ": Hi " + userEntity.getEmail()+ ", welcome!");
    }

// this code help to getAll User

    @GetMapping("/all")
    public ResponseEntity<List<UserEntityDTO>> getAllUsers(){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO= userServices.getAllUsers();
//        if( responseDTO == null){
//            responseDTO.setStatusCode(404);
//            responseDTO.setMessage("User not found");
//        }

        responseDTO.setStatusCode(200);
        responseDTO.setMessage("User found");
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getUserEntityDTOList());

    }

    // this code to help  to update the user by id
    @PatchMapping("/update-user/{id}/{role}")
    public  ResponseEntity<UserEntityDTO> updateUser(@PathVariable("role") String role, @PathVariable("id") Long id, @RequestBody UserEntity userEntity){

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO= userServices.updateUser(role.toUpperCase(),id,userEntity);
        if( responseDTO == null){
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("User not found");
        }

        responseDTO.setStatusCode(200);
        responseDTO.setMessage("User found");
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getUserEntityDTO());

    }
    @DeleteMapping("/delete/{role}/{id}")
    // this code to help   delete the user by id
    public  ResponseEntity<UserEntityDTO> deleteUser( @PathVariable("role") String role, @PathVariable("id") Long id){

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO= userServices.DeleteUser(role.toUpperCase(),id);
        if( responseDTO == null){
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("User not found");
        }

        responseDTO.setStatusCode(200);
        responseDTO.setMessage("User found");
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getUserEntityDTO());
    }


    @GetMapping("/get-user-by-name/{username}")
    // this code to help to search by user name
    public  ResponseEntity<UserEntityDTO> getUserEntityByUserName( @PathVariable("username") String userName){

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO= userServices.getUserByUserName(userName);
        responseDTO.setStatusCode(200);
        responseDTO.setMessage("User found");
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getUserEntityDTO());
    }





    @GetMapping("/all-booking-by-user-id/{userid}")
    @CircuitBreaker(name="ALlBookingByUserid",fallbackMethod = "FallbackAllBookingMethod")
    public  ResponseEntity<List<BookingEntityDTO>>  getAllBookingByUserId( @PathVariable("userid") Long userid){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO = userServices.getUserBookingsByUserId(userid);
        if (responseDTO == null){
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("User not found");
        }
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO.getBookingEntityDTOList());
    }



    public ResponseEntity<BookingEntityDTO> FallbackAllBookingMethod( Long userid,Exception ex ){

       logger.info(" fallback is excuted beacuse services is down"+ex.getMessage());

       UserEntity userEntity = UserEntity.builder().userName("Demo").email("Demo@gmail.com").firstName("DEMO").lastName("DEMO").build();
        return  ResponseEntity.status(500).body(new BookingEntityDTO());
    }


// this code END Here
}
