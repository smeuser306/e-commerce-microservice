package com.pdt.entities;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class EntityId {
    private UUID id;

    public EntityId() {
        this.id = UUID.randomUUID();
    }

    public EntityId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
