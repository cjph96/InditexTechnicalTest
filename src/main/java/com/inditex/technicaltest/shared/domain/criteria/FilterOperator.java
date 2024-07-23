package com.inditex.technicaltest.shared.domain.criteria;

public enum FilterOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    GTE(">="),
    LT("<"),
    LTE("<="),
    CONTAINS("CONTAINS"),
    NOT_CONTAINS("NOT_CONTAINS");

    private final String operator;

    FilterOperator(String operator) {
        this.operator = operator;
    }

    public static FilterOperator fromValue(String value) {
        return switch (value) {
            case "=" -> FilterOperator.EQUAL;
            case "!=" -> FilterOperator.NOT_EQUAL;
            case ">" -> FilterOperator.GT;
            case ">=" -> FilterOperator.GTE;
            case "<" -> FilterOperator.LT;
            case "<=" -> FilterOperator.LTE;
            case "CONTAINS" -> FilterOperator.CONTAINS;
            case "NOT_CONTAINS" -> FilterOperator.NOT_CONTAINS;
            default -> null;
        };
    }

    public String value() {
        return operator;
    }
}
