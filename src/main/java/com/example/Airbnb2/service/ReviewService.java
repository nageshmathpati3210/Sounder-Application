package com.example.Airbnb2.service;


import com.example.Airbnb2.payload.ReviewDto;

public interface ReviewService
{
    public ReviewDto createReview(ReviewDto reviewDto,String propertyId,String propertyUserId);
}
