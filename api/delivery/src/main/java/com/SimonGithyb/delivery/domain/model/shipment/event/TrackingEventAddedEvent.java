package com.SimonGithyb.delivery.domain.model.shipment.event;

import com.SimonGithyb.delivery.domain.shared.event.DomainEvent;

import java.util.UUID;

public class TrackingEventAddedEvent extends DomainEvent {

    private final UUID shipmentId;
    private final UUID trackingEventId;

    public TrackingEventAddedEvent(UUID shipmentId, UUID trackingEventId) {
        this.shipmentId = shipmentId;
        this.trackingEventId = trackingEventId;
    }

    public UUID getShipmentId() {
        return shipmentId;
    }

    public UUID getTrackingEventId() {
        return trackingEventId;
    }
}
