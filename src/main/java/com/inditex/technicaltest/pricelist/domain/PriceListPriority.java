package com.inditex.technicaltest.pricelist.domain;

import com.inditex.technicaltest.shared.domain.DomainValidationException;
import com.inditex.technicaltest.shared.domain.IntegerValueObject;

public class PriceListPriority extends IntegerValueObject {
    public PriceListPriority(Integer value) {
        super(value);
    }

    @Override
    public void guard(Integer value) {
        if (value < 0) {
            throw new DomainValidationException("Price priority can not be negative");
        }
    }
}
