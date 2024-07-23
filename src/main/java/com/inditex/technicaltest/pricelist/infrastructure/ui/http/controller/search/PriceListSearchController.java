package com.inditex.technicaltest.pricelist.infrastructure.ui.http.controller.search;

import com.inditex.technicaltest.pricelist.application.search.PriceListSearchRequest;
import com.inditex.technicaltest.pricelist.application.search.PriceListSearchResponse;
import com.inditex.technicaltest.pricelist.application.search.PriceListSearchUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

@RestController
public class PriceListSearchController {

    private final PriceListSearchUseCase useCase;

    @Autowired
    public PriceListSearchController(PriceListSearchUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(value = "/v1/price-list/search", produces = "application/json")
    public ResponseEntity<PriceListSearchResponse> search(@RequestParam HashMap<String, Serializable> params) {
        if (
                null == params || params.isEmpty()
                        || !params.containsKey("startDate")
                        || !params.containsKey("productId")
                        || !params.containsKey("brandId")
        ) {
            throw new IllegalArgumentException("Missing some required parameter('startDate','productId', brandId).");
        }

        PriceListSearchResponse response;
        try {
            response = useCase.handle(new PriceListSearchRequest(
                    LocalDateTime.parse(params.get("startDate").toString()),
                    Integer.parseInt(params.get("productId").toString()),
                    Integer.parseInt(params.get("brandId").toString())
            ));
        } catch (DateTimeParseException dateTimeParseException) {
            throw new IllegalArgumentException("startDate has an invalid date format.");
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("productId and brandId must be numeric.");
        }

        if (null == response) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }
}
