package com.pg.ExchangeRateService.domain.Rate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pg.ExchangeRateService.domain.shared.CustomBigDecimal;
import com.pg.ExchangeRateService.infrastructure.NBP.NbpRestRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rate Service.
 */
@Service
public class RateService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NbpRestRepository nbpRestRepository;

    /**
     * @param fromDate LocalDate object
     * @param toDate LocalDate object
     * @return RateResponseWrapper object with a processed list of data
     */
    public RateResponseWrapper getExchangeRateList(LocalDate fromDate, LocalDate toDate) {
        fromDate.minusDays(1);
        List<Rate> rateList = nbpRestRepository.getRatesFromToDate(fromDate, toDate).getRates();
        final Rate[] storedRateWrapper = {null};

        rateList = rateList
                .stream()
                .map(exchangeRate -> {
                    final Rate storedExchangeRate = storedRateWrapper[0];
                    if (storedExchangeRate == null) {
                        exchangeRate.setRateDifference(new RateDifference(BigDecimal.ZERO, BigDecimal.ZERO, exchangeRate.getEffectiveDate()));
                        storedRateWrapper[0] = exchangeRate;
                        return exchangeRate;
                    }

                    final BigDecimal askDifference = new CustomBigDecimal(exchangeRate.getAsk().subtract(storedExchangeRate.getAsk()).toPlainString());
                    final BigDecimal bidDifference = new CustomBigDecimal(exchangeRate.getBid().subtract(storedExchangeRate.getBid()).toPlainString());
                    exchangeRate.setRateDifference(new RateDifference(askDifference, bidDifference, storedExchangeRate.getEffectiveDate()));
                    storedRateWrapper[0] = exchangeRate;
                    return exchangeRate;
                })
                .collect(Collectors.toList());

        return new RateResponseWrapper(rateList);
    }
}
