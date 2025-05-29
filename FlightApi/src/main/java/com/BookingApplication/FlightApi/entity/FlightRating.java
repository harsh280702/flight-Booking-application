package com.BookingApplication.FlightApi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class FlightRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightEntity flight;
    private String description;
     private Double ratingValue;


}
