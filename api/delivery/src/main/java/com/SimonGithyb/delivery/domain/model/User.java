package com.SimonGithyb.delivery.domain.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;
import jakarta.validation.constraints.Email;
import lombok.Getter;

import java.util.regex.Pattern;

@Entity
@Getter
@Table(name = "users")
public class User {

    public enum UserRole {
        CLIENT,
        COURIER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50, name = "email")
    @Email
    private String email;

    @Column(nullable = false, length = 255, name = "password")
    private String passwordHash;

    @Column(nullable = false, length = 9, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, length = 56, name = "country")
    private String country;

    @Column(nullable = false, length = 50, name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    protected User() { }

    private User(String email, String password, String phoneNumber, String country, UserRole role) {
        this.email = validateEmail(email);
        this.passwordHash = validatePassword(password);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.country = validateCountry(country);
        this.role = role;
        this.createdAt = Instant.now();
    }

    public static User createClient(String email,
                                    String passwordHash,
                                    String phoneNumber,
                                    String country) {
        return new User(email, passwordHash, phoneNumber, country, UserRole.CLIENT);
    }

    private String validateEmail(String email) {
        if ( email == null)
            throw new IllegalArgumentException("Email cannot be null");
        return email;
    }

    private String validatePassword(String password) {
        if ( password == null)
            throw new IllegalArgumentException("Password cannot be null");
        return password;
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
