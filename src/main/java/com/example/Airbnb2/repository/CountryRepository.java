package com.example.Airbnb2.repository;


import com.example.Airbnb2.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,String>
{

    Optional<Country> findByCountryName(String countryName);
}
