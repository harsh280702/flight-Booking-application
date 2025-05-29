package com.BookingApplication.BookingApi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SecondaryRow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long bookingId;

        private Long userId;
        private Long flightId;
        private Long ticketId;
        private String bookingStatus;
        private LocalDateTime bookingDate;
        private Double totalFare;
        private LocalDateTime createdAt;
        private int numberOfPassengers;
        private String BookingConfirmationCode;



}
