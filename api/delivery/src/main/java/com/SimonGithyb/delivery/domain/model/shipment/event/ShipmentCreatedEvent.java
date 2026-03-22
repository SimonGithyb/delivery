package com.SimonGithyb.delivery.domain.model.shipment.event;

import java.time.Instant;
import java.util.UUID;

public class ShipmentCreatedEvent {

    private final UUID shipmentId;
    private final Instant occurredAt;

    public ShipmentCreatedEvent(UUID shipmentId) {
        this.shipmentId = shipmentId;
        this.occurredAt = Instant.now();
    }

    public UUID getShipmentId() {
        return shipmentId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
