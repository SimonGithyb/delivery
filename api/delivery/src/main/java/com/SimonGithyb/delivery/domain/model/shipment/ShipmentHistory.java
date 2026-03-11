package com.SimonGithyb.delivery.domain.model.shipment;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Table(name = "shipment_history")
public class ShipmentHistory {

    public enum TrackingEventType {
        CREATED,
        COURIER_ASSIGNED,
        PICKED_UP,
        IN_TRANSIT,
        ARRIVED_AT_DEPOT,
        OUT_FOR_DELIVERY,
        DELIVERED,
        DELIVERY_FAILED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "shipment_id", nullable = false)
    private UUID shipmentId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TrackingEventType status;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "event_time", nullable = false)
    private Instant eventTime;

    protected ShipmentHistory() {}

    private ShipmentHistory(
            UUID shipmentId,
            TrackingEventType status,
            String location,
            String description
    ) {
        this.shipmentId = shipmentId;
        this.status = status;
        this.location = location;
        this.description = description;
        this.eventTime = Instant.now();
    }

    public static ShipmentHistory create(
            UUID shipmentId,
            TrackingEventType status,
            String location,
            String description
    ) {
        return new ShipmentHistory(shipmentId, status, location, description);
    }

}