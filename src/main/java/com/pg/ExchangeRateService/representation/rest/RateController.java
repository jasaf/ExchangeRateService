package com.pg.ExchangeRateService.representation.rest;

import com.pg.ExchangeRateService.domain.Rate.RateResponseWrapper;
import com.pg.ExchangeRateService.domain.Rate.RateService;
import com.pg.ExchangeRateService.representation.rest.validators.DateValidator;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/rate/usd", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RateController {

    @Autowired
    private RateService rateService;

    /**
     * Return USD exchange rates for given from - to date.
     * Uses DateValidator object to validate user input.
     *
     * @param fromDateString String
     * @param toDateString String
     * @return ResponseEntity<RateResponseWrapper>
     */
    @GetMapping("/{fromDateString}/{toDateString}")
    public ResponseEntity<RateResponseWrapper> getExchangeRateForGivenDates(@PathVariable String fromDateString, @PathVariable String toDateString) {
        final DateValidator dateValidator = new DateValidator();
        if (!dateValidator.isDateRangeValid(fromDateString, toDateString)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dateValidator.getMessage());
        }

        return new ResponseEntity<>(
                rateService
                        .getExchangeRateList(new LocalDate(fromDateString), new LocalDate(toDateString)),
                HttpStatus.OK
        );
    }
}
