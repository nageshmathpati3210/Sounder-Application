package com.example.Airbnb2.configuration;

import com.example.Airbnb2.entity.PropertyUser;
import com.example.Airbnb2.repository.PropertyUserRepository;
import com.example.Airbnb2.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;


@Component
public class JwtFilterChain extends OncePerRequestFilter
{

    private JwtService jwtService;

    private PropertyUserRepository propertyUserRepository;

    public JwtFilterChain(JwtService jwtService, PropertyUserRepository propertyUserRepository) {
        this.jwtService = jwtService;
        this.propertyUserRepository = propertyUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer "))
        {
            String substring = authorization.substring(8, authorization.length() - 1);
            String username = jwtService.getUsername(substring);
            Optional<PropertyUser> byUsername = propertyUserRepository.findByUsername(username);
            if(byUsername.isPresent())
            {
                PropertyUser propertyUser = byUsername.get();
                UsernamePasswordAuthenticationToken us=new UsernamePasswordAuthenticationToken(propertyUser,null, Collections.singleton(new SimpleGrantedAuthority(propertyUser.getRole())));
                us.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(us);
            }

        }

        filterChain.doFilter(request,response);

    }
}
