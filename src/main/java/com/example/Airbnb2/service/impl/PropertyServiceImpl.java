package com.example.Airbnb2.service.impl;

import com.example.Airbnb2.entity.Country;
import com.example.Airbnb2.entity.Location;
import com.example.Airbnb2.entity.Property;
import com.example.Airbnb2.payload.PropertyDto;
import com.example.Airbnb2.repository.CountryRepository;
import com.example.Airbnb2.repository.LocationRepository;
import com.example.Airbnb2.repository.PropertyRepository;
import com.example.Airbnb2.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService
{
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) 
    {

        Property map = modelMapper.map(propertyDto, Property.class);
        map.setId(UUID.randomUUID().toString());
        Optional<Country> byCountryName = countryRepository.findByCountryName(propertyDto.getCountry().getCountryName().toLowerCase(Locale.ROOT));
        if(byCountryName.isPresent())
        {
            map.setCountry(byCountryName.get());
        }
        else
        {
            Country c=new Country();
            c.setCountryName(propertyDto.getCountry().getCountryName());
            c.setId(UUID.randomUUID().toString());
            Country save = countryRepository.save(c);
            map.setCountry(save);
        }
        Optional<Location> byLocationName = locationRepository.findByLocationName(propertyDto.getLocation().getLocationName().toLowerCase(Locale.ROOT));
        if(byLocationName.isPresent())
        {
            map.setLocation(byLocationName.get());
        }
        else
        {
            Location l=new Location();
            l.setLocationName(propertyDto.getLocation().getLocationName().toLowerCase());
            l.setId(UUID.randomUUID().toString());
            Location location =locationRepository.save(l);
            map.setLocation(location);
        }

        Property save = propertyRepository.save(map);
        return modelMapper.map(save,PropertyDto.class);

    }

    @Override
    public PropertyDto findByid(String id)
    {
        Property propertyIsNotFound = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property is not found"));
        PropertyDto map = modelMapper.map(propertyIsNotFound, PropertyDto.class);
        return map;
    }

    @Override
    public List<PropertyDto> findAll() 
    {
        List<Property> all = propertyRepository.findAll();
        List<PropertyDto> collect = all.stream().map((element) -> modelMapper.map(element, PropertyDto.class)).collect(Collectors.toList());
        return collect;
    }
    @Override
    public void deleteById(String id) 
    {
        Property propertyIsNotFound = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property is not found"));
        propertyRepository.deleteById(id);
    }
}
