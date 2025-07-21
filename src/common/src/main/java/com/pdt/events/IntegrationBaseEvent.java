package com.pdt.events;

import java.time.Instant;
import java.util.UUID;

public class IntegrationBaseEvent {
    public UUID id;
    public Instant createdTime;
    public IntegrationBaseEvent() {
        id = UUID.randomUUID();
        createdTime = Instant.now();
    }

    public IntegrationBaseEvent(UUID id, Instant createdTime) {
        this.id = id;
        this.createdTime = createdTime;
    }
}
