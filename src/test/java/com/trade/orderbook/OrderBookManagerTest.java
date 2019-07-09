package com.trade.orderbook;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SpringBootTest
public class OrderBookManagerTest extends AbstractTestNGSpringContextTests{

    @BeforeTest(groups = {"OrderBookTests"})
    public void setUpMasterBook() {

    }

    @Test(groups = {"OrderBookTests"})
    public void addNewBookTest() {

    }

}
