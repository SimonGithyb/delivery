package com.SimonGithyb.delivery.domain.model;

import com.SimonGithyb.delivery.domain.model.shipment.ShipmentHistory;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "tracking_events",
        indexes = {
                @Index(name = "idx_tracking_shipment", columnList = "shipment_id"),
                @Index(name = "idx_tracking_time", columnList = "event_time")
        }
)
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "shipment_id", nullable = false, updatable = false)
    private UUID shipmentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 50, updatable = false)
    private ShipmentHistory.TrackingEventType eventType;

    @Column(name = "location", length = 100, updatable = false)
    private String location;

    @Column(name = "description", length = 255, updatable = false)
    private String description;

    @Column(name = "event_time", nullable = false, updatable = false)
    private Instant eventTime;

    protected TrackingEvent() {
        // JPA
    }

    private TrackingEvent(
            UUID shipmentId,
            ShipmentHistory.TrackingEventType eventType,
            String location,
            String description
    ) {
        this.shipmentId = validateShipmentId(shipmentId);
        this.eventType = Objects.requireNonNull(eventType);
        this.location = location;
        this.description = description;
        this.eventTime = Instant.now();
    }

    public static TrackingEvent create(
            UUID shipmentId,
            ShipmentHistory.TrackingEventType eventType,
            String location,
            String description
    ) {
        return new TrackingEvent(shipmentId, eventType, location, description);
    }

    private UUID validateShipmentId(UUID shipmentId) {
        if (shipmentId == null) {
            throw new IllegalArgumentException("Shipment id cannot be null");
        }
        return shipmentId;
    }

    // ===== GETTERY =====

    public UUID getId() {
        return id;
    }

    public UUID getShipmentId() {
        return shipmentId;
    }

    public ShipmentHistory.TrackingEventType getEventType() {
        return eventType;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Instant getEventTime() {
        return eventTime;
    }

    // ===== EQUALS / HASHCODE (po ID) =====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackingEvent that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
