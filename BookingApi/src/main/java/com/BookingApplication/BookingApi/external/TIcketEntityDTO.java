package com.BookingApplication.BookingApi.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TIcketEntityDTO {

    private Long ticketId;
    private Long bookingId;
    private Long flightId;
    private Long userId;
    private String passengerName;
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
    private LocalDateTime updatedAt;
}
