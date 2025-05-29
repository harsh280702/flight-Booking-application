package com.BookingApplication.TicketApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private Long bookingId;
    private Long flightId;
    private Long userId;
    private  String flightName;
    private String passengerName;
    private  int passengerAge;
    private String passengerGender;
    private String seatNumber;

    private LocalDateTime boardingTime;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private String ticketStatus;
    private Double fare;

    private String notificationStatus;
    private Boolean emailSent;
    private Boolean smsSent;
    private LocalDateTime createdAt;

}

