package com.pg.ExchangeRateService.representation.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pg.ExchangeRateService.domain.Rate.Rate;
import com.pg.ExchangeRateService.domain.Rate.RateResponseWrapper;
import com.pg.ExchangeRateService.domain.Rate.RateService;
import com.pg.ExchangeRateService.domain.shared.CustomBigDecimal;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RateControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private RateController rateController;

    @MockBean
    private RateService rateService;

    private RateResponseWrapper rateResponseWrapper;

    @Before
    public void setUp() {
        rateResponseWrapper = new RateResponseWrapper(new ArrayList<>(Arrays.asList(
                new Rate(new CustomBigDecimal(3.400), new CustomBigDecimal(3.500), new LocalDate()),
                new Rate(new CustomBigDecimal(3.300), new CustomBigDecimal(3.400), new LocalDate())
        )));

        when(rateService.getExchangeRateList(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(rateResponseWrapper);
    }


    @Test
    public void getExchangeRateForGivenDatesShouldReturnRateResponseWrapper() throws Exception {
        mockMvc.perform(
                get("/rate/usd/2019-02-01/2019-02-02"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(asJsonString(rateResponseWrapper)));
    }

    @Test
    public void getExchangeRateForGivenDatesShouldThrowResponseStatusException() throws Exception {
        mockMvc.perform(
                get("/rate/usd/2019-02-01/test"))
                .andExpect(status().isInternalServerError());
    }


    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
