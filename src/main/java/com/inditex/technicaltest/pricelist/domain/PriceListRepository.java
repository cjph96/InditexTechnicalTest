package com.inditex.technicaltest.pricelist.domain;

import com.inditex.technicaltest.shared.domain.criteria.Criteria;

import java.util.List;

public interface PriceListRepository {
    void save(PriceList priceList);

    PriceList find(PriceListId id);

    List<PriceList> search(Criteria criteria);
}
