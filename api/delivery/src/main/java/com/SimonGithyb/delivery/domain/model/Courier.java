package com.SimonGithyb.delivery.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;
import java.util.regex.Pattern;

@Entity
@Getter
@Table(name = "couriers")
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50, name = "first_name")
    private String firstName;

    @Column(nullable = false, length = 255, name = "last_name")
    private String lastName;

    @Column(nullable = false, length = 9, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, length = 56, name = "country")
    private String country;

    protected Courier() { }

    private Courier(String firstName, String lastName, String phoneNumber, String country) {
        this.firstName = validateFirstName(firstName);
        this.lastName = validateLastName(lastName);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.country = validateCountry(country);

    }

    public static Courier createCourier(String firstName, String lastName, String phoneNumber, String country)  {
        return new Courier(firstName, lastName, phoneNumber, country);
    }

    private String validateFirstName(String firstName) {
        if ( firstName == null)
            throw new IllegalArgumentException("First name cannot be null");
        return firstName;
    }

    private String validateLastName(String lastName) {
        if ( lastName == null)
            throw new IllegalArgumentException("Last name cannot be null");
        return lastName;
    }

    private String validatePhoneNumber(String phoneNumber) {
        if ( phoneNumber == null)
            throw new IllegalArgumentException("Phone number cannot be null");

        final Pattern NUMBER_PATTERN = Pattern.compile("^\\d{1,9}$");
        if(!NUMBER_PATTERN.matcher(phoneNumber).matches())
            throw new IllegalArgumentException("Phone number must be composed of digits of length 9");
        return phoneNumber;
    }

    private String validateCountry(String country) {
        if ( country == null)
            throw new IllegalArgumentException("Country cannot be null");
        return country;
    }
}
