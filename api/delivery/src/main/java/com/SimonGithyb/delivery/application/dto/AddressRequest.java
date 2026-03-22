package com.SimonGithyb.delivery.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequest(

        @NotBlank(message = "Street cannot be empty")
        @Size(max = 100)
        String street,

        @NotBlank(message = "City cannot be empty")
        @Size(max = 50)
        String city,

        @NotBlank(message = "Postal code cannot be empty")
        @Pattern(
                regexp = "\\d{2}-\\d{3}",
                message = "Postal code must match format XX-XXX"
        )
        String postalCode,

        @NotBlank(message = "Country cannot be empty")
        @Size(max = 50)
        String country
) {}
