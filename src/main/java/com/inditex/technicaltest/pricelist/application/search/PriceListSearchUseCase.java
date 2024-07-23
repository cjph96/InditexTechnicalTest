package com.inditex.technicaltest.pricelist.application.search;

import com.inditex.technicaltest.pricelist.domain.PriceList;
import com.inditex.technicaltest.pricelist.domain.PriceListRepository;
import com.inditex.technicaltest.shared.application.UseCase;
import com.inditex.technicaltest.shared.domain.criteria.Criteria;
import com.inditex.technicaltest.shared.domain.criteria.Filters;
import com.inditex.technicaltest.shared.domain.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PriceListSearchUseCase implements UseCase<PriceListSearchRequest, PriceListSearchResponse> {
    final PriceListRepository priceListRepository;

    @Autowired
    public PriceListSearchUseCase(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    @Override
    public PriceListSearchResponse handle(PriceListSearchRequest request) {
        List<PriceList> priceLists = priceListRepository.search(criteriaFromRequest(request));
        try {
            return PriceListSearchResponse.fromDomain(priceLists.getFirst());
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    private Criteria criteriaFromRequest(PriceListSearchRequest request) {
        List<HashMap<String, String>> filters = new ArrayList<>() {{
            add(
                    new HashMap<>() {{
                        put("field", "startDate");
                        put("operator", ">=");
                        put("value", request.startDate().toString());
                    }}
            );
            add(
                    new HashMap<>() {{
                        put("field", "productId");
                        put("operator", "=");
                        put("value", request.productId().toString());
                    }}
            );
            add(
                    new HashMap<>() {{
                        put("field", "brandId");
                        put("operator", "=");
                        put("value", request.brandId().toString());
                    }}
            );
        }};

        return new Criteria(
                Filters.fromValues(filters),
                Order.fromValues("priority", "DESC"),
                1,
                null
        );
    }
}
