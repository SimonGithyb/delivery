package com.SimonGithyb.delivery.application.dto;

public record DimensionsResponse(
        double length,
        double width,
        double height,
        String unit
) {}
