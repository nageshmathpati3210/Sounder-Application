package com.example.Airbnb2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Airbnb2Application {

	public static void main(String[] args) {
		SpringApplication.run(Airbnb2Application.class, args);
	}


	@Bean
	public ModelMapper getBean()
	{
		return new ModelMapper();
	}

}
