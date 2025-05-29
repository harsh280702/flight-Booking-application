package com.BookingApplication.FlightApi.repostiry;

import com.BookingApplication.FlightApi.entity.FlightEntity;
import com.BookingApplication.FlightApi.entity.FlightRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FlightRatingRepostiry  extends JpaRepository<FlightRating, Long> {
    // Get the rating with the highest score
    @Query("SELECT r FROM FlightRating r WHERE r.ratingValue = (SELECT MAX(r2.ratingValue) FROM FlightRating r2)")
    FlightRating findTopByHighestRating();

    List<FlightRating> findByFlight(FlightEntity flight);

    @Query("SELECT r.flight.id AS flightId, AVG(r.ratingValue) AS avgRating " +
            "FROM FlightRating r GROUP BY r.flight.id ORDER BY avgRating DESC")
    List<Map<String, Object>> findFlightRatingsOrderedByAverage();
}
