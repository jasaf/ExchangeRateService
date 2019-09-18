package com.pg.ExchangeRateService.representation.rest.validators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@NoArgsConstructor
public class DateValidator {

    private String message;

    public boolean isDateRangeValid(String fromDateString, String toDateString) {
        return (dateFormatValidation(fromDateString)
                && dateFormatValidation(toDateString))
                && dateRangeValidation(fromDateString, toDateString);
    }

    private boolean dateRangeValidation(String fromDateString, String toDateString) {
        final LocalDate fromDate = new LocalDate(fromDateString);
        final LocalDate toDate = new LocalDate(toDateString);
        final LocalDate currentDate = new LocalDate();

        if (fromDate.compareTo(toDate) > 0 || (currentDate.compareTo(fromDate) < 0 || currentDate.compareTo(toDate) < 0)) {
            message = "Invalid date range provided.";
            return false;
        }

        return true;
    }

    private boolean dateFormatValidation(String dateString) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
           message = e.getMessage();
           return false;
        }

        return true;
    }
}
