package com.example.Airbnb2.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PropertyUserDto
{
    private String id;

    private String username;

    private String email;

    private String mobile;
    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

}
