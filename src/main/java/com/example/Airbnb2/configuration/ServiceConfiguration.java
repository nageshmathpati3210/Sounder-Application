package com.example.Airbnb2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class ServiceConfiguration
{
    @Autowired
    private JwtFilterChain jwtFilterChain;

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable().csrf().disable().
                 addFilterBefore(jwtFilterChain, AuthorizationFilter.class).
                 authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/propertyUser/**").permitAll()
                .anyRequest().authenticated();

        return httpSecurity.build();
    }

}
