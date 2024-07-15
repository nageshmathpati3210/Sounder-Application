package com.example.Airbnb2.controller;


import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.payload.ReviewDto;
import com.example.Airbnb2.repository.ReviewRepository;
import com.example.Airbnb2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController
{
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{propertyId}")
    public ResponseEntity<?> createReview(@RequestBody ReviewDto reviewDto, @PathVariable String propertyId, @AuthenticationPrincipal PropertyUser propertyUser)
    {
        ReviewDto review = reviewService.createReview(reviewDto, propertyId, propertyUser.getId());
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

}
