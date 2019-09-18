package com.pg.ExchangeRateService.representation.rest.validators;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class DateValidatorTest {

    private DateValidator dateValidator;

    @Before
    public void setUp(){
        dateValidator = new DateValidator();
    }

    @Test
    public void dateRangeValidationShouldReturnTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String fromDateString = "2019-02-01";
        final String toDateString = "2019-02-02";
        final boolean boolTest = (boolean) getDateRangeValidationMethod().invoke(dateValidator, fromDateString, toDateString);
        assertTrue(boolTest);
    }

    @Test
    public void dateRangeValidationShouldReturnFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        final String fromDateString = "2019-02-03";
        String toDateString = "2019-02-02";
        boolean boolTest = (boolean) getDateRangeValidationMethod().invoke(dateValidator, fromDateString, toDateString);

        final Field message = dateValidator.getClass().getDeclaredField("message");
        message.setAccessible(true);

        assertFalse(boolTest);
        assertEquals("Invalid date range provided.", message.get(dateValidator));

        toDateString = new LocalDate().plusDays(1).toString();
        boolTest = (boolean) getDateRangeValidationMethod().invoke(dateValidator, fromDateString, toDateString);
        assertFalse(boolTest);
    }

    @Test
    public void dateFormatValidationShouldReturnTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String date = "2019-02-01";
        boolean boolTest = (boolean) getDateFormatValidationMethod().invoke(dateValidator, date);
        assertTrue(boolTest);
    }

    @Test
    public void dateFormatValidationShouldReturnFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String invalidDate = "01/02/2019";
        boolean boolTest = (boolean) getDateFormatValidationMethod().invoke(dateValidator, invalidDate);
        assertFalse(boolTest);
    }

    private Method getDateFormatValidationMethod() throws NoSuchMethodException {
        final Method dateFormatValidation = dateValidator.getClass().getDeclaredMethod("dateFormatValidation", String.class);
        dateFormatValidation.setAccessible(true);
        return dateFormatValidation;
    }

    private Method getDateRangeValidationMethod() throws NoSuchMethodException {
        final Method dateRangeValidation = dateValidator.getClass().getDeclaredMethod("dateRangeValidation", String.class, String.class);
        dateRangeValidation.setAccessible(true);
        return dateRangeValidation;
    }
}
