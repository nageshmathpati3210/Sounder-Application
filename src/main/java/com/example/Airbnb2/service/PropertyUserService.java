package com.example.Airbnb2.service;


import com.example.Airbnb2.payload.Login;
import com.example.Airbnb2.payload.PropertyUserDto;

import java.util.List;

public interface PropertyUserService
{

    public PropertyUserDto createPropertyUser(PropertyUserDto propertyUserDto);


    public  PropertyUserDto findByid(String id);

    public List<PropertyUserDto> findAll();

    public void deletePropertyUser(String id);


    public String login(Login login);
}
