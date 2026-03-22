package com.SimonGithyb.delivery.presentation.controller;

import com.SimonGithyb.delivery.application.dto.*;
import com.SimonGithyb.delivery.application.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ShipmentResponse create(@Valid @RequestBody CreateShipmentRequest request) {

        return shipmentService.createShipment(request);
    }

    @GetMapping("/{trackingCode}")
    public ShipmentResponse get(@PathVariable String trackingCode) {

        return shipmentService.getByTrackingCode(trackingCode);
    }

    @PostMapping("/{shipmentId}/parcels")
    public ParcelResponse addParcel(
            @PathVariable UUID shipmentId,
            @Valid @RequestBody AddParcelRequest request
    ) {
        return shipmentService.addParcel(shipmentId, request);
    }

    @GetMapping("/{shipmentId}/tracking")
    public List<TrackingEventResponse> getTracking(
            @PathVariable UUID shipmentId
    ) {
        return shipmentService.getTracking(shipmentId);
    }
}
