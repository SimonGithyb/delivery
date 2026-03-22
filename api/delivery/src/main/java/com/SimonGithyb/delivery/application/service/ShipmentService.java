package com.SimonGithyb.delivery.application.service;

import com.SimonGithyb.delivery.application.dto.*;
import com.SimonGithyb.delivery.application.mapper.ParcelMapper;
import com.SimonGithyb.delivery.application.mapper.ShipmentMapper;
import com.SimonGithyb.delivery.application.mapper.TrackingEventMapper;
import com.SimonGithyb.delivery.domain.model.TrackingEvent;
import com.SimonGithyb.delivery.domain.model.shipment.Parcel;
import com.SimonGithyb.delivery.domain.model.shipment.Shipment;
import com.SimonGithyb.delivery.domain.repository.ShipmentRepository;
import com.SimonGithyb.delivery.domain.repository.TrackingEventRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class ShipmentService {

    private final ShipmentRepository repository;
    boolean latest;

    public ShipmentService(ShipmentRepository repository) {
        this.repository = repository;
    }

    private final ApplicationEventPublisher publisher;

    private void publishEvents(Shipment shipment) {

        shipment.domainEvents()
                .forEach(publisher::publishEvent);

        shipment.clearEvents();
    }

    public ShipmentResponse createShipment(CreateShipmentRequest request) {

        Shipment shipment = ShipmentMapper.toDomain(request);

        repository.save(shipment);

        return ShipmentMapper.toResponse(shipment);
    }

    public ShipmentResponse getByTrackingCode(String trackingCode) {

        Shipment shipment = repository.findByTrackingCode(trackingCode)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        return ShipmentMapper.toResponse(shipment);
    }

    public ParcelResponse addParcel(UUID shipmentId, AddParcelRequest request) {

        Shipment shipment = repository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        Parcel parcel = ParcelMapper.toDomain(request);

        shipment.addParcel(parcel);

        repository.save(shipment);

        publishEvents(shipment);

        return ParcelMapper.toResponse(parcel);
    }

    public List<TrackingEventResponse> getTracking(UUID shipmentId) {

        List<TrackingEvent> events =
                trackingEventRepository.findByShipmentIdOrderByEventTimeAsc(shipmentId);

        return events.stream()
                .map(TrackingEventMapper::toResponse)
                .toList();
    }

    public List<TrackingEventResponse> getTracking(UUID shipmentId) {

        TrackingEventRepository trackingEventRepository;
        List<TrackingEvent> events =
                trackingEventRepository.findByShipmentIdOrderByEventTimeAsc(shipmentId);

        int lastIndex = events.size() - 1;

        return IntStream.range(0, events.size())
                .mapToObj(i -> TrackingEventMapper.toResponse(
                        events.get(i),
                        i == lastIndex
                ))
                .toList();
    }
}
