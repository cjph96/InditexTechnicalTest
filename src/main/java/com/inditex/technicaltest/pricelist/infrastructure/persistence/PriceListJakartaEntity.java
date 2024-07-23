package com.inditex.technicaltest.pricelist.infrastructure.persistence;

import com.inditex.technicaltest.pricelist.domain.*;
import com.inditex.technicaltest.shared.infrastructure.persistence.JakartaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRICES")
public class PriceListJakartaEntity implements JakartaEntity<PriceList> {

    @Id
    @Column(name = "PRICE_LIST")
    private Integer id;

    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "CURR")
    private String currency;

    @Override
    public PriceList toDomain() {
        return new PriceList(
                new PriceListId(id),
                new PriceListBrandId(brandId),
                startDate,
                endDate,
                new PriceListProductId(productId),
                new PriceListPriority(priority),
                new PriceListPrice(
                        new BigDecimal(price),
                        Currency.getInstance(currency)
                )
        );
    }

    @Override
    public PriceListJakartaEntity fromDomain(PriceList priceList) {
        return new PriceListJakartaEntity(
                priceList.getId().value(),
                priceList.getBrandId().value(),
                priceList.getStartDate(),
                priceList.getEndDate(),
                priceList.getProductId().value(),
                priceList.getPriority().value(),
                priceList.getPrice().value().floatValue(),
                priceList.getPrice().currency().toString()
        );
    }
}
