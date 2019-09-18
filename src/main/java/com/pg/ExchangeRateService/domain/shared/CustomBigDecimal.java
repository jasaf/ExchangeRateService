package com.pg.ExchangeRateService.domain.shared;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal object with custom scale.
 *
 */
@Getter
public class CustomBigDecimal extends BigDecimal {

    private static final int DEFAULT_SCALE = 4;

    private final BigDecimal value;

    private CustomBigDecimal(BigDecimal value) {
        super(value.toPlainString());
        this.value = value;
    }

    public CustomBigDecimal(double val) {
        this(new BigDecimal(val).setScale(DEFAULT_SCALE, RoundingMode.CEILING));
    }

    public CustomBigDecimal(String val) {
        this(new BigDecimal(val).setScale(DEFAULT_SCALE, RoundingMode.CEILING));
    }
}
