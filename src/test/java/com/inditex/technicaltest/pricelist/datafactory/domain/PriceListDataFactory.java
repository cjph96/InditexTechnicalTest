package com.inditex.technicaltest.pricelist.datafactory.domain;

import com.inditex.technicaltest.pricelist.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class PriceListDataFactory {

    public static List<PriceList> data() {
        return new ArrayList<>() {{
            add(new PriceList(
                    new PriceListId(1),
                    new PriceListBrandId(1),
                    LocalDateTime.parse("2020-06-14T00:00:00"),
                    LocalDateTime.parse("2020-12-31T23:59:59"),
                    new PriceListProductId(35455),
                    new PriceListPriority(0),
                    new PriceListPrice(
                            new BigDecimal("35.50"),
                            Currency.getInstance("EUR")
                    )
            ));
            add(new PriceList(
                    new PriceListId(2),
                    new PriceListBrandId(1),
                    LocalDateTime.parse("2020-06-14T15:00:00"),
                    LocalDateTime.parse("2020-06-14T18:30:00"),
                    new PriceListProductId(35455),
                    new PriceListPriority(1),
                    new PriceListPrice(
                            new BigDecimal("25.45"),
                            Currency.getInstance("EUR")
                    )
            ));
            add(new PriceList(
                    new PriceListId(3),
                    new PriceListBrandId(1),
                    LocalDateTime.parse("2020-06-15T00:00:00"),
                    LocalDateTime.parse("2020-06-15T11:00:00"),
                    new PriceListProductId(35455),
                    new PriceListPriority(1),
                    new PriceListPrice(
                            new BigDecimal("30.50"),
                            Currency.getInstance("EUR")
                    )
            ));
            add(new PriceList(
                    new PriceListId(4),
                    new PriceListBrandId(1),
                    LocalDateTime.parse("2020-06-15T16:00:00"),
                    LocalDateTime.parse("2020-12-31T23:59:59"),
                    new PriceListProductId(35455),
                    new PriceListPriority(1),
                    new PriceListPrice(
                            new BigDecimal("38.95"),
                            Currency.getInstance("EUR")
                    )
            ));
        }};
    }
}
