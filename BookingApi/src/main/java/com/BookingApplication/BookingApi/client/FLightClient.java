package com.BookingApplication.BookingApi.client;

import com.BookingApplication.BookingApi.external.FlightEntityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FLIGHTAPI" , url = "http://localhost:8083/api/flight/")
public interface FLightClient {

    @GetMapping("/get-flight-By-id/{id}")
    FlightEntityDTO getFlightById(@PathVariable("id") Long id);

    @GetMapping("/all")
    List<FlightEntityDTO> getALLFlights();


}
