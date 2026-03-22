package com.SimonGithyb.delivery.application.event;

@Component
public class TrackingEventListener {

    private final TrackingEventRepository repository;

    public TrackingEventListener(TrackingEventRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void handle(ParcelAddedEvent event) {

        TrackingEvent trackingEvent = TrackingEvent.create(
                event.getShipmentId(),
                TrackingEventType.IN_TRANSIT,
                "WAREHOUSE",
                "Parcel added to shipment"
        );

        repository.save(trackingEvent);
    }
}
