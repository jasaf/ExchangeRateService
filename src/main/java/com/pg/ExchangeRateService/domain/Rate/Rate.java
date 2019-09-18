package com.pg.ExchangeRateService.domain.Rate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Object uses JsonCreator which enables parsing data with RestTemplate.
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Rate extends RateBase {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    private RateDifference rateDifference;

    @Builder
    public Rate(BigDecimal ask, BigDecimal bid, LocalDate effectiveDate, RateDifference rateDifference) {
        super(ask, bid);
        this.effectiveDate = effectiveDate;
        this.rateDifference = rateDifference;
    }

    @JsonCreator
    public Rate(@JsonProperty(value = "ask", required = true) BigDecimal ask,
                @JsonProperty(value = "bid", required = true) BigDecimal bid,
                @JsonProperty(value = "effectiveDate", required = true) LocalDate effectiveDate) {

        super(ask, bid);
        this.effectiveDate = effectiveDate;
    }
}
