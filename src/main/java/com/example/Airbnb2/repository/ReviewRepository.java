package com.example.Airbnb2.repository;

import com.example.Airbnb2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String>
{

    Optional<Review> findByPropertyIdAndPropertyUserId(String propertyId, String propertyUserId);
}