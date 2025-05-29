package com.BookingApplication.UserApi.client;

import com.BookingApplication.UserApi.external.BookingEntityDTO;

import java.util.List;

public class BookingFallback implements  BookingClient{
    @Override
    public List<BookingEntityDTO> getBookingByUserid(Long userid) {
        return null;
    }
}
