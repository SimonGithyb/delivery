package com.SimonGithyb.delivery.infrastructure.persistence;

import com.SimonGithyb.delivery.domain.model.shipment.TrackingEvent;
import com.SimonGithyb.delivery.domain.repository.TrackingEventRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTrackingEventRepository
        extends JpaRepository<TrackingEvent, UUID>, TrackingEventRepository {
}
