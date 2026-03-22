package com.SimonGithyb.delivery.domain.model.shipment;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Dimensions {

    private double length;
    private double width;
    private double height;

    protected Dimensions() {}

    private Dimensions(double length, double width, double height) {

        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }

        this.length = length;
        this.width = width;
        this.height = height;
    }

    public static Dimensions of(double length, double width, double height) {
        return new Dimensions(length, width, height);
    }

    public double volume() {
        return length * width * height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dimensions that = (Dimensions) o;

        return Double.compare(that.length, length) == 0 &&
                Double.compare(that.width, width) == 0 &&
                Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }
}