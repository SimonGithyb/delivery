package com.SimonGithyb.delivery.infrastructure.persistence;

import com.SimonGithyb.delivery.domain.model.shipment.Shipment;
import com.SimonGithyb.delivery.domain.repository.ShipmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaShipmentRepository
        extends JpaRepository<Shipment, UUID>, ShipmentRepository {

    Optional<Shipment> findByTrackingCode(String trackingCode);
}