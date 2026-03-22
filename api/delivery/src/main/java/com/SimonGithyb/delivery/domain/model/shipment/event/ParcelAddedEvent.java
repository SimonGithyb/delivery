package com.SimonGithyb.delivery.domain.model.shipment.event;

import com.SimonGithyb.delivery.domain.shared.event.DomainEvent;

import java.util.UUID;

public class ParcelAddedEvent extends DomainEvent {

    private final UUID shipmentId;
    private final UUID parcelId;

    public ParcelAddedEvent(UUID shipmentId, UUID parcelId) {
        this.shipmentId = shipmentId;
        this.parcelId = parcelId;
    }

    public UUID getShipmentId() {
        return shipmentId;
    }

    public UUID getParcelId() {
        return parcelId;
    }
}
