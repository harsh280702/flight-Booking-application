package com.BookingApplication.BookingApi.services;

import com.BookingApplication.BookingApi.dto.ResponseDTO;
import com.BookingApplication.BookingApi.entity.BookingEntity;
import com.BookingApplication.BookingApi.entity.Passenger;

import java.time.LocalDate;

public interface IBookingServices {

    ResponseDTO getAllBookingsByFlighId(Long flighId);

    ResponseDTO getAllBookingsByUserId(Long userId);

    ResponseDTO getAllBookings();

    ResponseDTO getBookingById(Long id);

    ResponseDTO createBooking(Long userId, Long flihgtId, BookingEntity booking, Passenger passenger );

    ResponseDTO cancancelBooking(Long bookingId);

}
