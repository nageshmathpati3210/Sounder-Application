package com.example.Airbnb2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @Column(name = "id")
    private String id;


    @Column(name = "location_name", nullable = false)
    private String locationName;

}