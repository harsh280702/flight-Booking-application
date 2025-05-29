package com.BookingApplication.FlightApi.mapper;



import com.BookingApplication.FlightApi.dto.FlightEntityDTO;
import com.BookingApplication.FlightApi.entity.FlightEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {



    public static FlightEntityDTO mapperFlightEntityToFlightEntityDTO(FlightEntity flightEntity) {
        FlightEntityDTO flightEntityDTO = new FlightEntityDTO();
        flightEntityDTO.setId(flightEntity.getId());
       flightEntityDTO.setFlightNo(flightEntity.getFlightNumber());
        flightEntityDTO.setAirlineName(flightEntity.getAirlineName());
        flightEntityDTO.setDepartureDate(flightEntity.getDepartureDate());
        flightEntityDTO.setArrivalDate(flightEntity.getArrivalDate());
        flightEntityDTO.setFlightType(flightEntity.getFlightType());
        flightEntityDTO.setFlightTime(flightEntity.getFlightTime());
        flightEntityDTO.setArrivalCity(flightEntity.getArrivalCity());
        flightEntityDTO.setDepartureCity(flightEntity.getDepartureCity());
        flightEntityDTO.setArrivalTime(flightEntity.getArrivalTime());
        flightEntityDTO.setDepartureTime(flightEntity.getDepartureTime());
        flightEntityDTO.setPrice(flightEntity.getPrice());
        flightEntityDTO.setTotalSeats(flightEntity.getTotalSeats());
        return flightEntityDTO;
    }

    public static List<FlightEntityDTO> mapFlightListEntityToFlightListDTO(List<FlightEntity> flightEntityList){
        return flightEntityList.stream().map(Utils::mapperFlightEntityToFlightEntityDTO).collect(Collectors.toList());
    }
}
