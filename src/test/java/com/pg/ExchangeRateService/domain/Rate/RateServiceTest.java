package com.pg.ExchangeRateService.domain.Rate;

import com.pg.ExchangeRateService.domain.shared.CustomBigDecimal;
import com.pg.ExchangeRateService.infrastructure.NBP.NbpRestRepository;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateServiceTest {

    @InjectMocks
    @Autowired
    private RateService rateService;

    @MockBean
    private NbpRestRepository nbpRestRepository;

    @Test
    public void getExchangeRateListShouldReturnValidRateResponseWrapper() {
        Mockito.when(nbpRestRepository.getRatesFromToDate(any(LocalDate.class), any(LocalDate.class))).thenReturn(getRateResponseWrapper());
        final RateResponseWrapper rateResponseWrapper = rateService.getExchangeRateList(new LocalDate("2019-02-01"), new LocalDate("2019-02-02"));
        final List<Rate> rateList = rateResponseWrapper.getRates();
        final Rate rate = rateList.get(1);

        assertEquals(new CustomBigDecimal(-0.100), rate.getRateDifference().getAsk());
        assertEquals(new CustomBigDecimal(-0.100), rate.getRateDifference().getBid());
        assertEquals(new LocalDate("2019-02-01"), rate.getRateDifference().getDifferenceFromDate());
    }


    private RateResponseWrapper getRateResponseWrapper() {
        final List<Rate> rates = new ArrayList<>(Arrays.asList(
                new Rate(new BigDecimal(3.700), new BigDecimal(3.600), new LocalDate("2019-02-01")),
                new Rate(new BigDecimal(3.600), new BigDecimal(3.500), new LocalDate("2019-02-02"))
        ));

        return new RateResponseWrapper(rates);
    }

}
