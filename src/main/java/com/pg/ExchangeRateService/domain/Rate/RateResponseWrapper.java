package com.pg.ExchangeRateService.domain.Rate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * A wrapper for the incoming and outgoing responses.
 *
 */

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RateResponseWrapper {

    private List<Rate> rates;
}
