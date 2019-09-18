package com.pg.ExchangeRateService.domain.Rate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RateDifference extends RateBase {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate differenceFromDate;

    public RateDifference(BigDecimal ask, BigDecimal bid, LocalDate differenceFromDate) {
        super(ask, bid);
        this.differenceFromDate = differenceFromDate;
    }
}
