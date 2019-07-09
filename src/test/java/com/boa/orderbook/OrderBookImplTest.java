package com.boa.orderbook;

import com.boa.orderbook.model.*;
import com.boa.orderbook.model.Side;
import com.boa.orderbook.service.BaseOrderBook;
import com.boa.orderbook.service.OrderBookImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class OrderBookImplTest extends AbstractTestNGSpringContextTests{

    Instrument instrument1, instrument2, instrument3;
    Order order1, order2, order3;
    BaseOrderBook b1, b2, b3;
    InstrumentRequest ir1, ir2, ir3;
    OrderBookImpl orderBookImpl;
    Map<String, Instrument> masterBook = new HashMap<>();

    @BeforeTest(groups = {"OrderBookTests"})
    public void setUpMasterBook() {
        ir1 = new InstrumentRequest("US12345", InstrumentType.BOND, Region.AMRS, "BBG");
        ir2 = new InstrumentRequest("US1555", InstrumentType.CDS, Region.AMRS, "twB");
        ir3 = new InstrumentRequest("US123", InstrumentType.STOCKS, Region.AMRS, "MKA");
        order1 = new Order(12d, new Date(),ir1, 1000d, Side.ASK, OrderType.LIMIT_ORDER );
        order2 = new Order(12d, new Date(),ir2, 1000d, Side.ASK, OrderType.LIMIT_ORDER );
        order3 = new Order(12d, new Date(),ir3, 1000d, Side.ASK, OrderType.LIMIT_ORDER );
        b1 = new BaseOrderBook();
        b1.addOrder(order1);
        b2 = new BaseOrderBook();
        b2.addOrder(order2);
        b3 = new BaseOrderBook();
        b3.addOrder(order3);
        instrument1 = new Instrument("US12345", InstrumentType.BOND);
        instrument2 = new Instrument("US1555", InstrumentType.CDS);
        instrument3 = new Instrument("US123", InstrumentType.STOCKS);
        instrument1.setOrderBook(b1);
        instrument2.setOrderBook(b2);
        instrument3.setOrderBook(b3);
        masterBook.put("US12345", instrument1);
        masterBook.put("US1555", instrument2);
        masterBook.put("US123", instrument3);
        orderBookImpl = new OrderBookImpl();
        orderBookImpl.setMasterBook(masterBook);
    }

    @Test(groups = {"OrderBookTests"})
    public void addNewBookTest() {
        InstrumentRequest ir4 = new InstrumentRequest("US15", InstrumentType.BOND, Region.AMRS, "BBG");
        Instrument instrument = orderBookImpl.addInstrument(ir4);
        Assert.assertEquals(instrument.getInstrumentId(), ir4.getInstrumentId());
        Assert.assertEquals(instrument.getInstrumentType(), ir4.getInstrumentType());
    }



}
