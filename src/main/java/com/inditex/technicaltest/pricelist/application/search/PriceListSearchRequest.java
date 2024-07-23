package com.inditex.technicaltest.pricelist.application.search;

import com.inditex.technicaltest.shared.application.Request;

import java.time.LocalDateTime;

public record PriceListSearchRequest(
        LocalDateTime startDate,
        Integer productId,
        Integer brandId
) implements Request {
}
