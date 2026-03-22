package com.SimonGithyb.delivery.domain.model.shipment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    @Column(name = "street", nullable = false, length = 120)
    private String street;

    @Column(name = "city", nullable = false, length = 80)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Column(name = "country", nullable = false, length = 60)
    private String country;

    protected Address() {
        // JPA
    }

    private Address(String street, String city, String postalCode, String country) {
        this.street = validate(street, "street");
        this.city = validate(city, "city");
        this.postalCode = validate(postalCode, "postalCode");
        this.country = validate(country, "country");
    }

    public static Address of(String street, String city, String postalCode, String country) {
        return new Address(street, city, postalCode, country);
    }

    private String validate(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be null or empty");
        }
        return value;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    // Value Object equality

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, postalCode, country);
    }
}