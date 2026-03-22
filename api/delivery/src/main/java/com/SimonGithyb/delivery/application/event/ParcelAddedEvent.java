package com.SimonGithyb.delivery.application.event;

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
