package com.SimonGithyb.delivery.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class shipment {
    @Test
    void should_transition_from_created_to_assigned() {
        ShipmentStatus created = ShipmentStatus.created();
        ShipmentStatus assigned = ShipmentStatus.assigned();

        ShipmentStatus result = created.transitionTo(assigned);

        assertEquals(assigned, result);
    }

    @Test
    void should_not_allow_invalid_transition() {
        ShipmentStatus delivered = ShipmentStatus.delivered();
        ShipmentStatus created = ShipmentStatus.created();

        assertThrows(IllegalStateException.class,
                () -> delivered.transitionTo(created));
    }
}
