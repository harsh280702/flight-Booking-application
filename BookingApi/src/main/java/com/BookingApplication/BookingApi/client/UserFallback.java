package com.BookingApplication.BookingApi.client;

import com.BookingApplication.BookingApi.external.UserEntityDTO;
import org.springframework.stereotype.Component;

@Component
public class UserFallback implements UserClient {

    @Override
    public UserEntityDTO getUserById(Long id) {
        return null;
    }
}
