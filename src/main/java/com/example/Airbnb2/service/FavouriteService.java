package com.example.Airbnb2.service;

import com.example.Airbnb2.entity.Favourite;
import com.example.Airbnb2.payload.FavouriteDto;

public interface FavouriteService
{

    public FavouriteDto createFav(FavouriteDto favouriteDto,String propertyId,String propertyUserId);


}
