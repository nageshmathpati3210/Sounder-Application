package com.example.Airbnb2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "country_name")
    private String countryName;

}