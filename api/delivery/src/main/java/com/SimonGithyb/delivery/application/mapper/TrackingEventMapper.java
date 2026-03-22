package com.SimonGithyb.delivery.application.mapper;

import com.SimonGithyb.delivery.application.dto.TrackingEventResponse;

public class TrackingEventMapper {

    public static TrackingEventResponse toResponse(
            TrackingEvent event,
            boolean latest
    ) {
        return new TrackingEventResponse(
                event.getEventType().name(),
                event.getLocation(),
                event.getDescription(),
                event.getEventTime(),
                latest
        );
    }
}
