package com.SimonGithyb.delivery.domain.model.shipment;

import com.SimonGithyb.delivery.domain.model.shipment.event.CourierAssignedEvent;
import com.SimonGithyb.delivery.domain.model.shipment.event.ParcelAddedEvent;
import com.SimonGithyb.delivery.domain.model.shipment.event.ShipmentCreatedEvent;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tracking_code", nullable = false, unique = true)
    private String trackingCode;

    @Column(name = "courier_id")
    private UUID courierId;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Embedded
    private ShipmentStatus status;

    @Transient
    private final List<Object> domainEvents = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "pickup_street")),
            @AttributeOverride(name = "city", column = @Column(name = "pickup_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "pickup_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "pickup_country"))
    })
    private Address pickupAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "delivery_street")),
            @AttributeOverride(name = "city", column = @Column(name = "delivery_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "delivery_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "delivery_country"))
    })
    private Address deliveryAddress;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id")
    private List<Parcel> parcels = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id")
    private List<ShipmentHistory> ShipmentHistory = new ArrayList<>();

    protected Shipment() {}

    private Shipment(UUID clientId, Address pickupAddress, Address deliveryAddress) {
        this.trackingCode = generateTrackingCode();
        this.clientId = validateClientId(clientId);
        this.pickupAddress = pickupAddress;
        this.deliveryAddress = deliveryAddress;
        this.createdAt = Instant.now();
        this.status = ShipmentStatus.created();

        domainEvents.add(new ShipmentCreatedEvent(this.id));
    }

    public static Shipment create(UUID clientId, Address pickupAddress, Address deliveryAddress) {
        return new Shipment(clientId, pickupAddress, deliveryAddress);
    }

    /*
     * Domain methods
     */

    public void assignCourier(UUID courierId) {
        if (courierId == null) {
            throw new IllegalArgumentException("Courier id cannot be null");
        }

        this.courierId = courierId;
        this.status = this.status.transitionTo(ShipmentStatus.assigned());
        domainEvents.add(new CourierAssignedEvent(this.id, courierId));
    }

    public void addParcel(Parcel parcel) {
        if (parcel == null) {
            throw new IllegalArgumentException("Parcel cannot be null");
        }

        this.parcels.add(parcel);
    }

    public void addTrackingEvent(ShipmentHistory event) {
        if (event == null) {
            throw new IllegalArgumentException("Tracking event cannot be null");
        }

        this.ShipmentHistory.add(event);
    }

    public double totalWeight() {
        return parcels.stream()
                .mapToDouble(p -> p.getWeight().getKilograms())
                .sum();
    }

    /*
     * Helpers
     */

    private String generateTrackingCode() {
        return "PL-" + Instant.now().toEpochMilli();
    }

    private UUID validateClientId(UUID clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }
        return clientId;
    }

    public UUID getId() {
        return id;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public UUID getCourierId() {
        return courierId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    public List<ShipmentHistory> getShipmentHistory() {
        return ShipmentHistory;
    }

    public List<Object> domainEvents() {
        return domainEvents;
    }

    public void clearEvents() {
        domainEvents.clear();
    }
}