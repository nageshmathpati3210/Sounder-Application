package com.example.Airbnb2.service.impl;

import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.payload.Login;
import com.example.Airbnb2.payload.PropertyUserDto;
import com.example.Airbnb2.repository.PropertyUserRepository;
import com.example.Airbnb2.service.PropertyUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PropertyUserServiceImpl implements PropertyUserService
{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PropertyUserRepository propertyUserRepository;

    private  JwtService jwtService;

    public PropertyUserServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public PropertyUserDto createPropertyUser(PropertyUserDto propertyUserDto)
    {
        PropertyUser map = modelMapper.map(propertyUserDto, PropertyUser.class);
        map.setId(UUID.randomUUID().toString());
        map.setRole("ROLE_USER");
        map.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt(10)));
        PropertyUser save = propertyUserRepository.save(map);
        PropertyUserDto map1 = modelMapper.map(save, PropertyUserDto.class);
        return map1;
    }

    @Override
    public PropertyUserDto findByid(String id)
    {
        PropertyUser userNotFound = propertyUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(userNotFound,PropertyUserDto.class);
    }

    @Override
    public List<PropertyUserDto> findAll()
    {
        List<PropertyUser> all = propertyUserRepository.findAll();
        List<PropertyUserDto> collect = all.stream().map(e -> modelMapper.map(e, PropertyUserDto.class)).collect(Collectors.toList());
        return collect;

    }

    @Override
    public void deletePropertyUser(String id) {
        PropertyUser userNotFound = propertyUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

    }

    @Override
    public String login(Login login)
    {

        Optional<PropertyUser> byUsername = propertyUserRepository.findByUsername(login.getUsername());
        if (byUsername.isPresent())
        {
            PropertyUser propertyUser = byUsername.get();
           if(BCrypt.checkpw(login.getPassword(),propertyUser.getPassword()))
           {
              return jwtService.generateJwtToken(propertyUser);
           }

        }
        return null;
    }

}
