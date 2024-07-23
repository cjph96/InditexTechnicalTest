package com.inditex.technicaltest.shared.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public abstract class Money {

    private final BigDecimal value;
    private final Currency currency;

    public Money(BigDecimal value, Currency currency) {
        this.value = value.setScale(2, RoundingMode.HALF_EVEN);
        this.currency = currency;
    }

    public String toString() {
        return String.format("%s %s", value.toString(), currency.getSymbol());
    }

    public BigDecimal value() {
        return value;
    }

    public Currency currency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money that = (Money) o;
        return value.equals(that.value) && currency.equals(that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
