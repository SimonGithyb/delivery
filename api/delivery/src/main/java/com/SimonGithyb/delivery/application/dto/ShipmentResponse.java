package com.SimonGithyb.delivery.application.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ShipmentResponse(
        UUID id,
        String trackingCode,
        String status,
        AddressResponse pickupAddress,
        AddressResponse deliveryAddress,
        List<ParcelResponse> parcels,
        double totalWeight,
        Instant createdAt
) {}
