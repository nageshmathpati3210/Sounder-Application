package com.example.Airbnb2.repository;

import com.example.Airbnb2.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String>
{
}