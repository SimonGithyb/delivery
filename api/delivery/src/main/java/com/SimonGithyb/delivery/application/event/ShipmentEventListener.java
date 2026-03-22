package com.SimonGithyb.delivery.application.event;

import com.SimonGithyb.delivery.domain.model.shipment.event.ShipmentCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventListener {

    @EventListener
    public void handleShipmentCreated(ShipmentCreatedEvent event) {

        System.out.println("Shipment created: " + event.getShipmentId());

        // logic to notify the user
    }
}
