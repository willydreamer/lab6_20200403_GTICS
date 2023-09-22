package com.example.lab6.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "LocationID", nullable = false)
    private Integer id;

    @Column(name = "City")
    private String city;

    @Column(name = "Country")
    private String country;

}