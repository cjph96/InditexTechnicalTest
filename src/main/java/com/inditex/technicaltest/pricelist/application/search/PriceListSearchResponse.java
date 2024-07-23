package com.inditex.technicaltest.pricelist.application.search;

import com.inditex.technicaltest.pricelist.domain.PriceList;
import com.inditex.technicaltest.shared.application.Response;

import java.time.LocalDateTime;

public record PriceListSearchResponse(
        Integer productId,
        Integer brandId,
        Integer id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String price
) implements Response {

    public static PriceListSearchResponse fromDomain(PriceList domain) {
        return new PriceListSearchResponse(
                domain.getProductId().value(),
                domain.getBrandId().value(),
                domain.getId().value(),
                domain.getStartDate(),
                domain.getEndDate(),
                domain.getPrice().toString()
        );
    }
}
