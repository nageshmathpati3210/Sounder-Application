package com.example.Airbnb2.service.impl;

import com.example.Airbnb2.entity.Property;
import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.entity.Review;
import com.example.Airbnb2.payload.PropertyDto;
import com.example.Airbnb2.payload.PropertyUserDto;
import com.example.Airbnb2.payload.ReviewDto;
import com.example.Airbnb2.repository.PropertyRepository;
import com.example.Airbnb2.repository.PropertyUserRepository;
import com.example.Airbnb2.repository.ReviewRepository;
import com.example.Airbnb2.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class ReviewServiceImpl implements ReviewService
{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyUserRepository propertyUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto, String propertyId, String propertyUserId)
    {
        Optional<Review> byPropertyIdAndPropertyUserId = reviewRepository.findByPropertyIdAndPropertyUserId(propertyId, propertyUserId);
        if(byPropertyIdAndPropertyUserId.isPresent())
        {
            Review review = byPropertyIdAndPropertyUserId.get();
            review.setDescription(reviewDto.getDescription());
            Review save = reviewRepository.save(review);
            ReviewDto r=new ReviewDto();
            r.setDescription(save.getDescription());
            r.setPropertyDto(modelMapper.map(save.getProperty(), PropertyDto.class));
            r.setPropertyUserDto(modelMapper.map(save.getPropertyUser(), PropertyUserDto.class));
            r.setId(save.getId());
            return r;
        }
        else
        {
            Review r=new Review();
            r.setId(UUID.randomUUID().toString());
            r.setDescription(reviewDto.getDescription());
            Property property = propertyRepository.findById(propertyId).orElseThrow(()->new RuntimeException("Property is not available"));
            PropertyUser propertyuserIsNotAvailable = propertyUserRepository.findById(propertyUserId).orElseThrow(() -> new RuntimeException("propertyuser is not available"));
            r.setProperty(property);
            r.setPropertyUser(propertyuserIsNotAvailable);
            Review save = reviewRepository.save(r);
            ReviewDto rp=new ReviewDto();
            rp.setDescription(save.getDescription());
            rp.setPropertyDto(modelMapper.map(save.getProperty(), PropertyDto.class));
            rp.setPropertyUserDto(modelMapper.map(save.getPropertyUser(), PropertyUserDto.class));
            rp.setId(save.getId());
            return rp;

        }

    }
}
