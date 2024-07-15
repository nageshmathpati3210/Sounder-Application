package com.example.Airbnb2.excepton;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse
{

    private String exceptionMessage;

    private Date time;

    private String description;

    private Date date;

}
