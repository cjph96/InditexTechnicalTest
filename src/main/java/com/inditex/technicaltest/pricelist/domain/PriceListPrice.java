package com.inditex.technicaltest.pricelist.domain;

import com.inditex.technicaltest.shared.domain.Money;

import java.math.BigDecimal;
import java.util.Currency;

public class PriceListPrice extends Money {
    public PriceListPrice(BigDecimal value, Currency currency) {
        super(value, currency);
    }
}
