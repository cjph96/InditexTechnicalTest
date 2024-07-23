package com.inditex.technicaltest.pricelist.integration.infrastructure.persistence;

import com.inditex.technicaltest.TechnicalTestApplicationTestCase;
import com.inditex.technicaltest.pricelist.datafactory.domain.PriceListDataFactory;
import com.inditex.technicaltest.pricelist.domain.*;
import com.inditex.technicaltest.pricelist.infrastructure.persistence.PriceListJakartaRepository;
import com.inditex.technicaltest.shared.domain.criteria.Criteria;
import com.inditex.technicaltest.shared.domain.criteria.Filters;
import com.inditex.technicaltest.shared.domain.criteria.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceListJakartaRepositoryTest extends TechnicalTestApplicationTestCase {

    @Autowired
    private PriceListJakartaRepository sut;

    @Test
    public void shouldFindPriceList() {
        AtomicInteger id = new AtomicInteger(1);
        PriceListDataFactory.data().forEach(v -> {
            assertEquals(v, sut.find(new PriceListId(id.get())));
            id.getAndIncrement();
        });
    }

    @Test
    public void shouldSavePriceList() {
        PriceList priceList = new PriceList(
                new PriceListId(5),
                new PriceListBrandId(1),
                LocalDateTime.parse("2024-07-19T00:00:00"),
                LocalDateTime.parse("2024-07-21T23:59:59"),
                new PriceListProductId(35456),
                new PriceListPriority(2),
                new PriceListPrice(
                        new BigDecimal("355.50"),
                        Currency.getInstance("USD")
                )
        );
        sut.save(priceList);
        assertEquals(priceList, sut.find(priceList.getId()));
    }

    @Test
    public void shouldSearchPriceListAndReturnAllOnEmptyCriteria() {
        assertEquals(PriceListDataFactory.data(), sut.search(new Criteria(Filters.none(), Order.none())));
    }

    @Test
    public void shouldSearchPriceListAndReturnFilteredByCriteria() {
        List<HashMap<String, String>> filters = new ArrayList<>() {{
            add(
                    new HashMap<>() {{
                        put("field", "startDate");
                        put("operator", ">");
                        put("value", "2020-06-14T00:00:00");
                    }}
            );
            add(
                    new HashMap<>() {{
                        put("field", "endDate");
                        put("operator", "<");
                        put("value", "2020-12-31T23:59:59");
                    }}
            );
        }};

        Criteria criteria = new Criteria(
                Filters.fromValues(filters),
                Order.fromValues("price", "DESC")
        );
        List<PriceList> expectedPriceLists = new ArrayList<>(PriceListDataFactory.data());
        expectedPriceLists.remove(3);
        expectedPriceLists.remove(0);

        assertEquals(expectedPriceLists.reversed(), sut.search(criteria));
    }
}
