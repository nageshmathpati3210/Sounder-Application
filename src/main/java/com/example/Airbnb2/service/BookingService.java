package com.example.Airbnb2.service;

import com.example.Airbnb2.payload.BookingDto;

public interface BookingService
{

    public BookingDto createBooking(String propertyId,String PropertyUserId,int days);


    public void cancelBooking(String id);
}
