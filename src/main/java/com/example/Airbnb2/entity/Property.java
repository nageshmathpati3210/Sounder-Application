package com.example.Airbnb2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property
{
    @Id
    private String id;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "bedrooms")
    private Long bedrooms;

    @Column(name = "bathromms")
    private Long bathromms;

    @Column(name = "nightly_price")
    private String nightlyPrice;

    @Column(name = "guest")
    private Integer guest;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}