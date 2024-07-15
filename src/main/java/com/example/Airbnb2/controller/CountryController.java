package com.example.Airbnb2.controller;

import com.example.Airbnb2.entity.Country;
import com.example.Airbnb2.repository.CountryRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/country")
public class CountryController
{

    @Autowired
    private CountryRepository countryRepository;

    public ResponseEntity<?> createCountry(@RequestBody Country country) {
//        if(byCountryName==null)
//        {
//            Country save = countryRepository.save(country);
//            return new ResponseEntity<>("Country is already present", HttpStatus.OK);
//
//        }
//        else {
//            return new ResponseEntity<>("Country is already present", HttpStatus.OK);
//        }
//    }

        return null;
    }

}
