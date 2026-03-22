package com.SimonGithyb.delivery.application.dto;

public record AddressResponse(
        String street,
        String city,
        String postalCode,
        String country
) {}
