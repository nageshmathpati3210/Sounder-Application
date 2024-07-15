package com.example.Airbnb2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking
{

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "property_user_id")
    private PropertyUser propertyUser;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}