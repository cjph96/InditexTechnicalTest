package com.inditex.technicaltest.pricelist.datafactory.application.search;

import com.inditex.technicaltest.pricelist.application.search.PriceListSearchResponse;

import java.time.LocalDateTime;

public class PriceListSearchResponseDataFactory {
    public static PriceListSearchResponse createDefault() {
        return new PriceListSearchResponse(
                35455,
                1,
                2,
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"),
                "25.45 €"
        );
    }
}
