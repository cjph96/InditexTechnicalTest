package com.inditex.technicaltest.pricelist.domain;

public interface PriceListRepository {
    void save(PriceList priceList);

    PriceList find(PriceListId id);
}
