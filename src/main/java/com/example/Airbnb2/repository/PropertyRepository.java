package com.example.Airbnb2.repository;

import com.example.Airbnb2.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, String> {
}