package com.BookingApplication.UserApi.client;

import com.BookingApplication.UserApi.external.BookingEntityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BookingApi",url = "http://localhost:8082",path = "/api/booking",fallback = BookingFallback.class)
public interface BookingClient {

    @GetMapping("/getbooking-user-by-id/{userid}")
    List<BookingEntityDTO> getBookingByUserid(@PathVariable Long userid);

}
