package com.inditex.technicaltest.pricelist.datafactory.application.search;

import com.inditex.technicaltest.pricelist.application.search.PriceListSearchRequest;

import java.time.LocalDateTime;

public class PriceListSearchRequestDataFactory {
    public static PriceListSearchRequest createDefault() {
        return new PriceListSearchRequest(
                LocalDateTime.parse("2020-06-14T00:00:00"),
                35455,
                1
        );
    }
}
