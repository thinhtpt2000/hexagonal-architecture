package dev.thinhtpt.order.service.domain.valueobject;

import dev.thinhtpt.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
