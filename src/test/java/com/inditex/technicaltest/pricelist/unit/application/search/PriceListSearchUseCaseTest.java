package com.inditex.technicaltest.pricelist.unit.application.search;

import com.inditex.technicaltest.pricelist.application.search.PriceListSearchUseCase;
import com.inditex.technicaltest.pricelist.datafactory.application.search.PriceListSearchRequestDataFactory;
import com.inditex.technicaltest.pricelist.datafactory.application.search.PriceListSearchResponseDataFactory;
import com.inditex.technicaltest.pricelist.datafactory.domain.PriceListDataFactory;
import com.inditex.technicaltest.pricelist.domain.PriceList;
import com.inditex.technicaltest.pricelist.domain.PriceListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PriceListSearchUseCaseTest {
    PriceListSearchUseCase sut;
    PriceListRepository repositoryMock;

    @BeforeEach
    protected void setUp() {
        repositoryMock = mock(PriceListRepository.class);
        sut = new PriceListSearchUseCase(repositoryMock);
    }

    @Test
    public void shouldSearch() {
        List<PriceList> expectedPriceList = new ArrayList<>(PriceListDataFactory.data());
        expectedPriceList.removeFirst();
        Mockito.when(repositoryMock.search(Mockito.any())).thenReturn(expectedPriceList);
        assertEquals(
                PriceListSearchResponseDataFactory.createDefault(),
                sut.handle(PriceListSearchRequestDataFactory.createDefault())
        );
    }
}
