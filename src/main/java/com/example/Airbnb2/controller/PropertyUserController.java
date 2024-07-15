package com.example.Airbnb2.controller;


import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.payload.Login;
import com.example.Airbnb2.payload.PropertyUserDto;
import com.example.Airbnb2.payload.Token;

import com.example.Airbnb2.repository.PropertyUserRepository;

import com.example.Airbnb2.service.PropertyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/propertyUser")
public class PropertyUserController
{
    @Autowired
    private PropertyUserService propertyUserService;

    @Autowired
    private PropertyUserRepository propertyUserRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createPropertyUser(@RequestBody PropertyUserDto propertyUserDto)
    {
        PropertyUser byEmail = propertyUserRepository.findByEmail(propertyUserDto.getEmail());
        if(byEmail!=null)
        {
            return new ResponseEntity<>("This email is already present", HttpStatus.BAD_REQUEST);
        }

            PropertyUserDto propertyUser = propertyUserService.createPropertyUser(propertyUserDto);
        return new ResponseEntity<>("User is Created Sucessfully",HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> createLogin(@RequestBody Login login)
    {
        String login1 = propertyUserService.login(login);
        Token t=new Token();
        t.setToken(login1);
        if(login1==null)
        {
            return new ResponseEntity<>("Bad Credentials",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(t,HttpStatus.OK);


    }



}
