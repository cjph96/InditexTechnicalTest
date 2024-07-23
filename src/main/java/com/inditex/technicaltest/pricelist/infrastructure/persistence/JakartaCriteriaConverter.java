package com.inditex.technicaltest.pricelist.infrastructure.persistence;

import com.inditex.technicaltest.shared.domain.criteria.Criteria;
import com.inditex.technicaltest.shared.domain.criteria.Filter;
import com.inditex.technicaltest.shared.domain.criteria.FilterOperator;
import jakarta.persistence.criteria.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

public final class JakartaCriteriaConverter<T> {
    private final CriteriaBuilder builder;
    private final HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>> predicateTransformers = new HashMap<>() {{
        put(FilterOperator.EQUAL, JakartaCriteriaConverter.this::equalsPredicateTransformer);
        put(FilterOperator.NOT_EQUAL, JakartaCriteriaConverter.this::notEqualsPredicateTransformer);
        put(FilterOperator.GT, JakartaCriteriaConverter.this::greaterThanPredicateTransformer);
        put(FilterOperator.GTE, JakartaCriteriaConverter.this::greaterThanOrEqualPredicateTransformer);
        put(FilterOperator.LT, JakartaCriteriaConverter.this::lowerThanPredicateTransformer);
        put(FilterOperator.LTE, JakartaCriteriaConverter.this::lowerThanOrEqualPredicateTransformer);
        put(FilterOperator.CONTAINS, JakartaCriteriaConverter.this::containsPredicateTransformer);
        put(FilterOperator.NOT_CONTAINS, JakartaCriteriaConverter.this::notContainsPredicateTransformer);
    }};

    public JakartaCriteriaConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaQuery<T> convert(Criteria criteria, Class<T> entityClass) {
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        criteriaQuery.where(formatPredicates(criteria.filters().filters(), root));

        if (criteria.order().hasOrder()) {
            Path<Object> orderBy = root.get(criteria.order().orderBy().value());
            Order order = criteria.order().orderType().isAsc() ? builder.asc(orderBy) : builder.desc(orderBy);

            criteriaQuery.orderBy(order);
        }

        return criteriaQuery;
    }

    private Predicate[] formatPredicates(List<Filter> filters, Root<T> root) {
        List<Predicate> predicates = filters.stream().map(filter -> formatPredicate(
                filter,
                root
        )).toList();

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicatesArray = predicates.toArray(predicatesArray);

        return predicatesArray;
    }

    private Predicate formatPredicate(Filter filter, Root<T> root) {
        BiFunction<Filter, Root<T>, Predicate> transformer = predicateTransformers.get(filter.operator());

        return transformer.apply(filter, root);
    }

    private Predicate equalsPredicateTransformer(Filter filter, Root<T> root) {
        if (checkIsDateTime(filter, root)) {
            return builder.equal(root.get(filter.field().value()), LocalDateTime.parse(filter.value().value()));
        }

        return builder.equal(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate notEqualsPredicateTransformer(Filter filter, Root<T> root) {
        if (checkIsDateTime(filter, root)) {
            return builder.notEqual(root.get(filter.field().value()), LocalDateTime.parse(filter.value().value()));
        }

        return builder.notEqual(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate greaterThanPredicateTransformer(Filter filter, Root<T> root) {
        if (checkIsDateTime(filter, root)) {
            return builder.greaterThan(root.get(filter.field().value()), LocalDateTime.parse(filter.value().value()));
        }

        return builder.greaterThan(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate greaterThanOrEqualPredicateTransformer(Filter filter, Root<T> root) {
        if (checkIsDateTime(filter, root)) {
            return builder.greaterThanOrEqualTo(root.get(filter.field().value()), LocalDateTime.parse(filter.value().value()));
        }

        return builder.greaterThanOrEqualTo(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate lowerThanPredicateTransformer(Filter filter, Root<T> root) {
        if (checkIsDateTime(filter, root)) {
            return builder.lessThan(root.get(filter.field().value()), LocalDateTime.parse(filter.value().value()));
        }

        return builder.lessThan(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate lowerThanOrEqualPredicateTransformer(Filter filter, Root<T> root) {
        if (checkIsDateTime(filter, root)) {
            return builder.lessThanOrEqualTo(root.get(filter.field().value()), LocalDateTime.parse(filter.value().value()));
        }

        return builder.lessThanOrEqualTo(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate containsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.like(root.get(filter.field().value()), String.format("%%%s%%", filter.value().value()));
    }

    private Predicate notContainsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notLike(root.get(filter.field().value()), String.format("%%%s%%", filter.value().value()));
    }

    private boolean checkIsDateTime(Filter filter, Root<T> root) {
        return root.get(filter.field().value()).getJavaType().equals(LocalDateTime.class);
    }
}
