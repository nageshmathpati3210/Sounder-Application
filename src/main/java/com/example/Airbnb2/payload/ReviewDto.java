package com.example.Airbnb2.payload;

import com.example.Airbnb2.entity.Property;
import com.example.Airbnb2.entity.PropertyUser;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
public class ReviewDto
{
    private String id;

    private String description;


    private PropertyDto propertyDto;

    private PropertyUserDto propertyUserDto;

}
