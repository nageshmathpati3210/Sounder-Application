package com.example.Airbnb2.controller;


import com.example.Airbnb2.payload.PropertyDto;
import com.example.Airbnb2.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/property")
public class PropertyController
{

    @Autowired
    private PropertyService propertyService;


    @PostMapping("create")
    public ResponseEntity<?> createProperty(@RequestBody PropertyDto propertyDto)
    {
        PropertyDto property = propertyService.createProperty(propertyDto);
        return new ResponseEntity<>(property,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByPropertyId(@PathVariable String id)
    {
        PropertyDto byid = propertyService.findByid(id);
        return new ResponseEntity<>(byid,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll()
    {
        List<PropertyDto> all = propertyService.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    public ResponseEntity<?> deleteById(String id)
    {
        propertyService.deleteById(id);
        return new ResponseEntity<>("Deleted sucessfull",HttpStatus.OK);
    }



}
