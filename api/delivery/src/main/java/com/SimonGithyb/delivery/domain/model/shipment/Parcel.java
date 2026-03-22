package com.SimonGithyb.delivery.domain.model.shipment;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Weight weight;

    @Embedded
    private Dimensions dimensions;

    @Column(length = 200)
    private String description;

    protected Parcel() {}

    private Parcel(Weight weight, Dimensions dimensions, String description) {
        this.weight = weight;
        this.dimensions = dimensions;
        this.description = description;
    }

    public static Parcel create(Weight weight, Dimensions dimensions, String description) {
        return new Parcel(weight, dimensions, description);
    }

    public Weight getWeight() {
        return weight;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}