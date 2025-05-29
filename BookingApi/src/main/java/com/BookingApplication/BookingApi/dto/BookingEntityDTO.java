package com.BookingApplication.BookingApi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingEntityDTO {
    private Long bookingId;

    private Long userId;
    private Long flightId;
    private Long ticketId;
    private String bookingStatus;
    private LocalDateTime bookingDate;
    private Double totalFare;
    private LocalDateTime createdAt;
    private int numberOfPassengers;
}
