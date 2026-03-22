package com.SimonGithyb.delivery.application.dto;

import java.time.Instant;

public record TrackingEventResponse(
        String type,
        String location,
        String description,
        Instant timestamp,
        boolean latest
) {}
