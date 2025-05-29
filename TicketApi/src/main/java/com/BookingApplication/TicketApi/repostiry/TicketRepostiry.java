package com.BookingApplication.TicketApi.repostiry;

import com.BookingApplication.TicketApi.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepostiry extends JpaRepository<TicketEntity, Long> {


    int countByFlightId(Long flightId);
    List<TicketEntity> findByUserId(Long flightId);
}
