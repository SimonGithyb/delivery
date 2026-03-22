package com.SimonGithyb.delivery.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateShipmentRequest(

        @NotNull(message = "Client ID cannot be null")
        UUID clientId,

        @Valid
        @NotNull(message = "Pickup address is required")
        AddressRequest pickupAddress,

        @Valid
        @NotNull(message = "Delivery address is required")
        AddressRequest deliveryAddress
) {}
