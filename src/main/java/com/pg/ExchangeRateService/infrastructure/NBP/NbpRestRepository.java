package com.pg.ExchangeRateService.infrastructure.NBP;

import com.pg.ExchangeRateService.domain.Rate.RateResponseWrapper;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Narodowy Bank Polski Rest repository used for collecting USD exchange rate data.
 *
 */
@Component
public class NbpRestRepository {

    @Value("${local.nbp.api}")
    private String url;

    @Value("${local.nbp.format}")
    private String format;

    @Autowired
    private RestTemplate restTemplate;

    public RateResponseWrapper getRatesFromToDate(LocalDate fromDate, LocalDate toDate) {
        return restTemplate.getForObject(getApiUrl(fromDate, toDate), RateResponseWrapper.class);
    }

    private String getApiUrl(LocalDate fromDate, LocalDate toDate) {
        return url + fromDate.toString() + "/" + toDate.toString() + "?format" + format;
    }
}
