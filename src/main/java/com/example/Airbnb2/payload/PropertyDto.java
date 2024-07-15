package com.example.Airbnb2.payload;

import com.example.Airbnb2.entity.Country;
import com.example.Airbnb2.entity.Location;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class PropertyDto
{
    private String id;

    private String propertyName;

    private Long bedrooms;

    private Long bathromms;

    private String nightlyPrice;

    private Integer guest;

    private Country country;

    private Location location;
}
