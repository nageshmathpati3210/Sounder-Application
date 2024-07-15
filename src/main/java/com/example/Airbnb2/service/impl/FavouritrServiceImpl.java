package com.example.Airbnb2.service.impl;


import com.example.Airbnb2.entity.Favourite;
import com.example.Airbnb2.entity.Property;
import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.payload.FavouriteDto;
import com.example.Airbnb2.payload.PropertyDto;
import com.example.Airbnb2.payload.PropertyUserDto;
import com.example.Airbnb2.repository.FavouriteRepository;
import com.example.Airbnb2.repository.PropertyRepository;
import com.example.Airbnb2.repository.PropertyUserRepository;
import com.example.Airbnb2.service.FavouriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FavouritrServiceImpl implements FavouriteService
{

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyUserRepository propertyUserRepository;

    @Override
    public FavouriteDto createFav(FavouriteDto favouriteDto,String propertyId,String propertyUserId)
    {
        Favourite map=new Favourite();
        map.setId(UUID.randomUUID().toString());
        Optional<Favourite> by = favouriteRepository.findByPropertyIdAndPropertyUserId(propertyId, propertyUserId);
        if(by.isPresent()) {

            if (favouriteDto.getIsFav() == false) {
                Optional<Favourite> byPropertyIdAndPropertyUserId = favouriteRepository.findByPropertyIdAndPropertyUserId(propertyId, propertyUserId);
                if (byPropertyIdAndPropertyUserId.isPresent()) {
                    Favourite favourite = byPropertyIdAndPropertyUserId.get();
                    favouriteRepository.deleteById(favourite.getId());
                    return null;

                }
            }
            else return modelMapper.map(by.get(),FavouriteDto.class);
        }
        PropertyUser userIsNotPresent = propertyUserRepository.findById(propertyUserId).orElseThrow(() -> new RuntimeException("user is not present"));
        Property propertyNotFound = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));
        map.setPropertyUser(userIsNotPresent);
        map.setProperty(propertyNotFound);
        map.setIsFav(true);
        Favourite save = favouriteRepository.save(map);

        PropertyUserDto map1 = modelMapper.map(save.getPropertyUser(),PropertyUserDto.class);
        PropertyDto map2 = modelMapper.map(save.getProperty(), PropertyDto.class);
        FavouriteDto f=new FavouriteDto();
        f.setId(save.getId());
        f.setIsFav(save.getIsFav());
        f.setPropertyUserDto(map1);
        f.setPropertyDto(map2);
        return f;
    }




}
