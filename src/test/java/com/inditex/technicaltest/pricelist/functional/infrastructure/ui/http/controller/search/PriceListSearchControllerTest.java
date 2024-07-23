package com.inditex.technicaltest.pricelist.functional.infrastructure.ui.http.controller.search;

import com.inditex.technicaltest.ControllerTestCase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PriceListSearchControllerTest extends ControllerTestCase {
    private final static String PATH = "/v1/price-list/search";

    public static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(
                        "startDate=2020-06-14T10:00:00&productId=35455&brandId=1",
                        200,
                        "{\"productId\":35455,\"brandId\":1,\"id\":2,\"startDate\":\"2020-06-14T15:00:00\",\"endDate\":\"2020-06-14T18:30:00\",\"price\":\"25.45 €\"}"
                ),
                Arguments.of(
                        "startDate=2020-06-14T16:00:00&productId=35455&brandId=1",
                        200,
                        "{\"productId\":35455,\"brandId\":1,\"id\":3,\"startDate\":\"2020-06-15T00:00:00\",\"endDate\":\"2020-06-15T11:00:00\",\"price\":\"30.50 €\"}"
                ),
                Arguments.of(
                        "startDate=2020-06-14T21:00:00&productId=35455&brandId=1",
                        200,
                        "{\"productId\":35455,\"brandId\":1,\"id\":3,\"startDate\":\"2020-06-15T00:00:00\",\"endDate\":\"2020-06-15T11:00:00\",\"price\":\"30.50 €\"}"
                ),
                Arguments.of(
                        "startDate=2020-06-15T10:00:00&productId=35455&brandId=1",
                        200,
                        "{\"productId\":35455,\"brandId\":1,\"id\":4,\"startDate\":\"2020-06-15T16:00:00\",\"endDate\":\"2020-12-31T23:59:59\",\"price\":\"38.95 €\"}"
                ),
                Arguments.of(
                        "startDate=2020-06-16T21:00:00&productId=35455&brandId=1",
                        404,
                        ""
                ),
                Arguments.of(
                        "startDate=2020-06-16T21:",
                        400,
                        "{\"message\":\"Missing some required parameter('startDate','productId', brandId).\"}"
                ),
                Arguments.of(
                        "startDate=20201-06-14T10:00:00&productId=35455&brandId=1",
                        400,
                        "{\"message\":\"startDate has an invalid date format.\"}"
                ),
                Arguments.of(
                        "startDate=2020-06-14T10:00:00&productId=35455&brandId=1x",
                        400,
                        "{\"message\":\"productId and brandId must be numeric.\"}"
                )
        );
    }

    @ParameterizedTest(name = "{index} => query={0}, expectedStatusCode={1}, expectedResponse={2}")
    @MethodSource("dataProvider")
    void shouldReturnStatusOK(String query, Integer expectedStatusCode, String expectedResponse) throws Exception {
        assertResponse(
                String.format("%s?%s", PATH, query),
                expectedStatusCode,
                expectedResponse
        );
    }
}
