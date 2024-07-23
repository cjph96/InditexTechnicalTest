package com.inditex.technicaltest.shared.domain.criteria;

import java.util.Optional;

public final class Criteria {
    private final Filters filters;
    private final Order order;
    private final Integer limit;
    private final Integer offset;

    public Criteria(Filters filters, Order order, Integer limit, Integer offset) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.offset = offset;
    }

    public Criteria(Filters filters, Order order) {
        this.filters = filters;
        this.order = order;
        this.limit = null;
        this.offset = null;
    }

    public Filters filters() {
        return filters;
    }

    public Order order() {
        return order;
    }

    public Optional<Integer> limit() {
        return Optional.ofNullable(limit);
    }

    public Optional<Integer> offset() {
        return Optional.ofNullable(offset);
    }

    public boolean hasFilters() {
        return !filters.filters().isEmpty();
    }

}
