package com.example.Airbnb2.payload;

import lombok.Data;

@Data
public class Token
{

    private String type="Bearer";

    private String token;
}
