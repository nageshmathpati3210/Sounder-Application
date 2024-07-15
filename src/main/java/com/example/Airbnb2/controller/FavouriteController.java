package com.example.Airbnb2.controller;


import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.payload.FavouriteDto;
import com.example.Airbnb2.repository.FavouriteRepository;
import com.example.Airbnb2.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/favourite")
public class FavouriteController
{
    @Autowired
    private FavouriteService favouriteService;

    private FavouriteRepository favouriteRepository;

    @PostMapping("/{propertyId}")
    public ResponseEntity<?> createFavourite(@RequestBody FavouriteDto favouriteDto, @PathVariable String propertyId, @AuthenticationPrincipal PropertyUser propertyUser)
    {
        FavouriteDto fav = favouriteService.createFav(favouriteDto, propertyId, propertyUser.getId());
        if(fav==null)
        {
            return new ResponseEntity<>("Favourite is removed",HttpStatus.OK);
        }
        return new ResponseEntity<>(fav, HttpStatus.OK);
    }

}
