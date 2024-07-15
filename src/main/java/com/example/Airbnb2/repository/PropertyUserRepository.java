package com.example.Airbnb2.repository;

import com.example.Airbnb2.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, String>
{
    PropertyUser findByEmail(String email);

    Optional<PropertyUser> findByUsername(String username);
}