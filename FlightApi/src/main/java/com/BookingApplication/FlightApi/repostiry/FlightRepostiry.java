package com.BookingApplication.FlightApi.repostiry;

import com.BookingApplication.FlightApi.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepostiry extends JpaRepository<FlightEntity, Long> {


    FlightEntity findFlightEntitiesByFlightNumber(Long flightNo);
    List<FlightEntity> findFlightEntitiesByAirlineName(String airlineName);

    @Query("SELECT f FROM FlightEntity f " + "WHERE f.departureDate > f.arrivalDate " + "AND f.departureCity = :departureCity " +
            "AND f.arrivalCity = :arrivalCity " +
            "AND f.departureDate = :departureDate ")
    List<FlightEntity> findFlightsWithInvalidDateAndMatchingDetailsOneWay(String departureCity, String arrivalcity, LocalDate departureDate);

    @Query("SELECT f FROM FlightEntity f " + "WHERE f.departureDate > f.arrivalDate " + "AND f.departureCity = :departureCity " +
            "AND f.arrivalCity = :arrivalCity " +
            "AND f.departureDate = :departureDate " +
            "AND f.arrivalDate = :arrivalDate")
    List<FlightEntity>  findFlightsWithInvalidDateAndMatchingDetailsTwoWay(String departureCity, String arrivalCity, LocalDate departureDate, LocalDate arrivalDate);


    @Query("SELECT f FROM FlightEntity f WHERE f.averageRating = (SELECT MAX(f2.averageRating) FROM FlightEntity f2)")
    FlightEntity findFlightWithHighestAverageRating();
}
