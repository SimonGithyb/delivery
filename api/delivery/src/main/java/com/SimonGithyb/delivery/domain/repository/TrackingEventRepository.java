package com.SimonGithyb.delivery.domain.repository;

import com.SimonGithyb.delivery.domain.model.shipment.TrackingEvent;

import java.util.List;
import java.util.UUID;

public interface TrackingEventRepository {

    TrackingEvent save(TrackingEvent event);

    List<TrackingEvent> findByShipmentIdOrderByEventTimeAsc(UUID shipmentId);

    List<TrackingEvent> findByShipmentIdOrderByEventTimeDesc(UUID shipmentId);
}
