package com.SimonGithyb.delivery.domain.model.shipment.event;

import com.SimonGithyb.delivery.domain.shared.event.DomainEvent;

import java.util.UUID;

public class CourierAssignedEvent extends DomainEvent {

    private final UUID shipmentId;
    private final UUID courierId;

    public CourierAssignedEvent(UUID shipmentId, UUID courierId) {
        this.shipmentId = shipmentId;
        this.courierId = courierId;
    }

    public UUID getShipmentId() {
        return shipmentId;
    }

    public UUID getCourierId() {
        return courierId;
    }
}
