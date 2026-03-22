package com.SimonGithyb.delivery.domain.model.shipment;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Weight {

    private double kilograms;

    protected Weight() {} // JPA

    private Weight(double kilograms) {
        if (kilograms <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.kilograms = kilograms;
    }

    public static Weight ofKg(double kilograms) {
        return new Weight(kilograms);
    }

    public double getKilograms() {
        return kilograms;
    }

    public Weight add(Weight other) {
        return new Weight(this.kilograms + other.kilograms);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weight weight = (Weight) o;
        return Double.compare(weight.kilograms, kilograms) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kilograms);
    }
}