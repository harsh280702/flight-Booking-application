package com.BookingApplication.BookingApi.client;

import com.BookingApplication.BookingApi.external.UserEntityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="UserApi",url = "http://localhost:8085",path = "/api/user")
public interface UserClient {

    @GetMapping("/get-user-by-id/{id}")
    UserEntityDTO getUserById(Long id);


}
