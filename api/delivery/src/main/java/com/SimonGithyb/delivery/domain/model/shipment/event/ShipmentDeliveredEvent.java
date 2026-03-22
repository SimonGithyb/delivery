package com.SimonGithyb.delivery.domain.model.shipment.event;

import com.SimonGithyb.delivery.domain.shared.event.DomainEvent;

import java.util.UUID;

public class ShipmentDeliveredEvent extends DomainEvent {

    private final UUID shipmentId;

    public ShipmentDeliveredEvent(UUID shipmentId) {
        this.shipmentId = shipmentId;
    }

    public UUID getShipmentId() {
        return shipmentId;
    }
}
