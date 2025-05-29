package com.BookingApplication.BookingApi.repostiry;

import com.BookingApplication.BookingApi.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface BookingRepostiry extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findBookingEntitiesByFlightId(Long flighId);

    List<BookingEntity> getBookingEntitiesByUserId(Long userId);
}
