package com.BookingApplication.BookingApi.mapper;

import com.BookingApplication.BookingApi.dto.BookingEntityDTO;
import com.BookingApplication.BookingApi.dto.PassengerDTO;
import com.BookingApplication.BookingApi.entity.BookingEntity;
import com.BookingApplication.BookingApi.entity.Passenger;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {



    public static BookingEntityDTO mappedBookingEntityToBookingDTO(BookingEntity bookingEntity) {
        BookingEntityDTO bookingDTO = new BookingEntityDTO();
        bookingDTO.setBookingId(bookingEntity.getBookingId());
        bookingDTO.setUserId(bookingEntity.getUserId());
        bookingDTO.setTotalFare(bookingEntity.getTotalFare());
        bookingDTO.setCreatedAt(bookingEntity.getCreatedAt());
        bookingDTO.setTotalFare(bookingEntity.getTotalFare());
       // bookingDTO.setUpdatedAt(bookingEntity.getUpdatedAt());
        bookingDTO.setBookingDate(bookingEntity.getBookingDate());
        bookingDTO.setBookingStatus(bookingEntity.getBookingStatus());
     //   bookingDTO.setPassengerList( bookingEntity.getPassengerList());
        return bookingDTO;
    }

    public static List<BookingEntityDTO> mappBookingEntityListToBookingDTOList(List<BookingEntity> flightEntityList){
        return flightEntityList.stream().map(Utils::mappedBookingEntityToBookingDTO).collect(Collectors.toList());
    }

     public static PassengerDTO mappedpassengerEntityToPassengerDTO(Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setId(passenger.getId());
        passengerDTO.setUserId(passenger.getUserId());
        passengerDTO.setName(passenger.getName());
        passengerDTO.setAge(passenger.getAge());
        return passengerDTO;
    }


    public static List<PassengerDTO> mapPassengerListEntityToPassengerListDTO(List<Passenger> passengerList){
        return passengerList.stream().map(Utils::mappedpassengerEntityToPassengerDTO).collect(Collectors.toList());
    }

}
