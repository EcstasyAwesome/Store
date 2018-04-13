package com.github.company.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address {

    private long id;
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @Positive
    private int house;
    private int apartment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false, length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(nullable = false)
    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    @Column
    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public Address() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                house == address.house &&
                apartment == address.apartment &&
                Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, house, apartment);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", apartment=" + apartment +
                '}';
    }
}