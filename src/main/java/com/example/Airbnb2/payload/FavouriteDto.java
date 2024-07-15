package com.example.Airbnb2.payload;

import com.example.Airbnb2.entity.Property;
import com.example.Airbnb2.entity.PropertyUser;
import lombok.Data;

@Data
public class FavouriteDto
{
    private String id;

    private Boolean isFav;

    private PropertyDto propertyDto;

    private PropertyUserDto propertyUserDto;

}
