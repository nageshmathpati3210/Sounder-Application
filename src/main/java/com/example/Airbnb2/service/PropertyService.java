package com.example.Airbnb2.service;

import com.example.Airbnb2.payload.PropertyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PropertyService
{

    public PropertyDto createProperty(PropertyDto propertyDto);


    public PropertyDto findByid(String id);



    public List<PropertyDto> findAll();



    public void deleteById(String id);

}
