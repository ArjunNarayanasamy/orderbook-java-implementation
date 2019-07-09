package com.arjun.orderbook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arjun.orderbook.model.Region;
import com.arjun.orderbook.model.InstrumentRequest;
import com.arjun.orderbook.model.InstrumentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderBookControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test(groups = {"open-orderbook-integration-test"})
    public void testOrderBook() throws Exception {
        InstrumentRequest ir4 = new InstrumentRequest("US15", InstrumentType.BOND, Region.AMRS, "BBG");
        String json = objectMapper.writeValueAsString(ir4);
        mvc.perform(post("/addBook").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Book Added for US15"));
        mvc.perform(get("/openBook").param("instrumentId", "US15"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Book opened successfully"))
                .andExpect(jsonPath("$.code").value("BOOK_OPENED"));
    }
}
