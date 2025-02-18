package com.inditex.technicaltest.shared.domain;

import java.util.Objects;

public abstract class IntegerValueObject {
    private final Integer value;

    public IntegerValueObject(Integer value) {
        this.guard(value);
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntegerValueObject that = (IntegerValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    protected void guard(Integer value) {
    }
}
