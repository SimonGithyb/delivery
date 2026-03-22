package com.SimonGithyb.delivery.application.dto;

import java.util.UUID;

public record ParcelResponse(
        UUID id,
        WeightResponse weight,
        DimensionsResponse dimensions,
        String description
) {}