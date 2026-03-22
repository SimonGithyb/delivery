package com.SimonGithyb.delivery.application.mapper;

import com.SimonGithyb.delivery.application.dto.AddressResponse;
import com.SimonGithyb.delivery.application.dto.CreateShipmentRequest;
import com.SimonGithyb.delivery.application.dto.ShipmentResponse;
import com.SimonGithyb.delivery.domain.model.shipment.Address;
import com.SimonGithyb.delivery.domain.model.shipment.Shipment;

public class ShipmentMapper {

    public static ShipmentResponse toResponse(Shipment shipment) {

        return new ShipmentResponse(
                shipment.getId(),
                shipment.getTrackingCode(),
                shipment.getStatus().getStatus(),
                mapAddress(shipment.getPickupAddress()),
                mapAddress(shipment.getDeliveryAddress()),
                shipment.getParcels().stream()
                        .map(ParcelMapper::toResponse)
                        .toList(),
                shipment.totalWeight(),
                shipment.getCreatedAt()
        );
    }

    private static AddressResponse mapAddress(Address address) {
        return new AddressResponse(
                address.getStreet(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry()
        );
    }
}