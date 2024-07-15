package com.example.Airbnb2.repository;

import com.example.Airbnb2.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, String>
{


     Optional<Favourite> findByPropertyIdAndPropertyUserId(String propertyId, String PropertyUserId);

}