package com.SimonGithyb.delivery.domain.repository;

public interface JpaTrackingEventRepository
        extends JpaRepository<TrackingEvent, UUID>, TrackingEventRepository {
}
