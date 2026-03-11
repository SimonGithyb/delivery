package com.SimonGithyb.delivery.domain.model.shipment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Embeddable
public class ShipmentStatus {

    public enum Status {
        CREATED,
        ASSIGNED,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    protected ShipmentStatus() { }

    private ShipmentStatus(Status status) {
        this.status = status;
    }

    public static ShipmentStatus created() {
        return new ShipmentStatus(Status.CREATED);
    }

    public static ShipmentStatus assigned() {
        return new ShipmentStatus(Status.ASSIGNED);
    }

    public static ShipmentStatus shipped() {
        return new ShipmentStatus(Status.SHIPPED);
    }

    public static ShipmentStatus delivered() {
        return new ShipmentStatus(Status.DELIVERED);
    }

    public static ShipmentStatus cancelled() {
        return new ShipmentStatus(Status.CANCELLED);
    }

    public ShipmentStatus transitionTo(ShipmentStatus newStatus) {

        if (!canTransitionTo(newStatus.status)) {
            throw new IllegalStateException(
                    "Cannot transition from " + status + " to " + newStatus.status
            );
        }

        return newStatus;
    }

    private boolean canTransitionTo(Status target) {

        return switch (status) {

            case CREATED ->
                    target == Status.ASSIGNED ||
                            target == Status.CANCELLED;

            case ASSIGNED ->
                    target == Status.SHIPPED ||
                            target == Status.CANCELLED;

            case SHIPPED ->
                    target == Status.DELIVERED;

            case DELIVERED, CANCELLED ->
                    false;
        };
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipmentStatus that = (ShipmentStatus) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}