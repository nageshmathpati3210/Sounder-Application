package com.example.Airbnb2.repository;

import com.example.Airbnb2.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, String>
{

    Optional<Location> findByLocationName(String locationname);
}