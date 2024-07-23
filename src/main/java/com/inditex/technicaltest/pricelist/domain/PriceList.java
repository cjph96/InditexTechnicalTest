package com.inditex.technicaltest.pricelist.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class PriceList {
    private final PriceListId id;
    private PriceListBrandId brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PriceListProductId productId;
    private PriceListPriority priority;
    private PriceListPrice price;
}
