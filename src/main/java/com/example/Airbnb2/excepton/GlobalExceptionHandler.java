package com.example.Airbnb2.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Time;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAnyException(Exception e,WebRequest request) throws Exception
    {
        ExceptionResponse ex=new ExceptionResponse("Something wrong",new Date(), e.getLocalizedMessage(), new Date());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

}
