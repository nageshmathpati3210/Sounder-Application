package com.example.Airbnb2.service.impl;


import com.example.Airbnb2.entity.Booking;
import com.example.Airbnb2.entity.Property;
import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.payload.BookingDto;
import com.example.Airbnb2.payload.PropertyDto;
import com.example.Airbnb2.payload.PropertyUserDto;
import com.example.Airbnb2.repository.BookingRepository;
import com.example.Airbnb2.repository.PropertyRepository;
import com.example.Airbnb2.repository.PropertyUserRepository;
import com.example.Airbnb2.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService
{

    private BookingRepository bookingRepository;

    private PropertyRepository propertyRepository;


    private PropertyUserRepository propertyUserRepository;

    private ModelMapper modelMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, PropertyRepository propertyRepository, PropertyUserRepository propertyUserRepository,ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.propertyUserRepository = propertyUserRepository;
        this.modelMapper=modelMapper;
    }


    @Override
    public BookingDto createBooking(String propertyId, String propertyUserId, int days) {
        Booking b=new Booking();
        b.setId(UUID.randomUUID().toString());
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property is not found"));
        PropertyUser propertyUser = propertyUserRepository.findById(propertyUserId).orElseThrow(() -> new RuntimeException("user is not found"));
        String nightlyPrice = property.getNightlyPrice();
        long l = Long.valueOf(nightlyPrice);
        double t= days * l;
        b.setTotal(t);
        b.setProperty(property);
        b.setPropertyUser(propertyUser);
        Booking save = bookingRepository.save(b);

        BookingDto bd=new BookingDto();
        bd.setId(save.getId());
        bd.setTotal(save.getTotal());
        bd.setPropertyDto(modelMapper.map(save.getProperty(), PropertyDto.class));
        bd.setPropertyUserDto(modelMapper.map(save.getPropertyUser(), PropertyUserDto.class));
        return bd;

    }

    @Override
    public void cancelBooking(String id)
    {
        Property booking = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking is not available"));
    }
}
