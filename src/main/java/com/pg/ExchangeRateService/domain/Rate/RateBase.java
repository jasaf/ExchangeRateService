package com.pg.ExchangeRateService.domain.Rate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Rate base class holding ask & bid prices as BigDecimal.
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class RateBase {

    @NotNull
    private BigDecimal ask;

    @NotNull
    private BigDecimal bid;
}
