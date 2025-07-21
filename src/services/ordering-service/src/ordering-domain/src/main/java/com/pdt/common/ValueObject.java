package com.pdt.common;

import java.util.List;
import java.util.Objects;

public abstract class ValueObject {
    protected abstract List<Object> getEqualityComponents();

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        ValueObject other = (ValueObject) obj;
        
        return this.getEqualityComponents().equals(other.getEqualityComponents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEqualityComponents().toArray());
    }
}