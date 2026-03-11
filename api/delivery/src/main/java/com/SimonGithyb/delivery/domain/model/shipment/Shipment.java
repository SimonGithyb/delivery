package com.SimonGithyb.delivery.domain.model.shipment;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.Instant;

@Entity
@Getter
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, name = "tracking_code")
    private String trackingCode;

    @Column(nullable = false, name = "courier_id")
    private UUID courierId;

    @Column(nullable = false, name = "client_id")
    private UUID clientId;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    @Embedded
    private ShipmentStatus status;

    @OneToMany
    @JoinColumn(name = "shipment_id")
    private List<ShipmentHistory> shipmentHistories = new ArrayList<>();


    protected Shipment() {}

    private Shipment(UUID courierId, UUID clientId ) {
        this.trackingCode = this.generateTrackingCode();
        this.courierId = validateCourierId(courierId);
        this.clientId = validateClientId(clientId);
        this.status = ShipmentStatus.created();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment shipment)) return false;
        return id != null && id.equals(shipment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static Shipment create( UUID courierId, UUID clientId) {
        return new Shipment(courierId, clientId);
    }

    public void changeStatus(ShipmentStatus newStatus) {
        this.status = this.status.transitionTo(newStatus);
    }

    private UUID validateCourierId(UUID courierId) {
        if ( courierId == null)
            throw new IllegalArgumentException("Courier id cannot be null");
        return courierId;
    }

    private String generateTrackingCode() {

        String datePart = LocalDate.now()
                .format(DateTimeFormatter.BASIC_ISO_DATE);

        String randomPart = UUID.randomUUID()
                .toString()
                .substring(0,6)
                .toUpperCase();

        return "PL" + datePart + "-" + randomPart;
    }

    private UUID validateClientId(UUID clientId) {
        if ( clientId == null)
            throw new IllegalArgumentException("Client id cannot be null");
        return clientId;
    }

}
