package com.SimonGithyb.delivery.domain.repository;

import com.SimonGithyb.delivery.domain.model.shipment.Shipment;

import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepository {

    Shipment save(Shipment shipment);

    Optional<Shipment> findById(UUID id);

    Optional<Shipment> findByTrackingCode(String trackingCode);
}
