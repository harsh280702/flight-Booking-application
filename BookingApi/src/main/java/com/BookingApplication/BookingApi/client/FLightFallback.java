package com.BookingApplication.BookingApi.client;

import com.BookingApplication.BookingApi.external.FlightEntityDTO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FLightFallback implements FLightClient {
    @Override
    public FlightEntityDTO getFlightById(Long id) {
        return null;
    }

    @Override
    public List<FlightEntityDTO> getALLFlights() {
        return null;
    }
}
