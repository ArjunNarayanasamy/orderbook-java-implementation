package com.arjun.orderbook;

import com.arjun.orderbook.model.*;
import com.arjun.orderbook.service.OrderBookImpl;
import com.arjun.orderbook.model.*;
import com.arjun.orderbook.model.Side;
import com.arjun.orderbook.service.BaseOrderBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(OrderBookImplTest.class);

    @BeforeTest(groups = {"OrderBookTests"})
    public void setUpMasterBook() {
        ir1 = new InstrumentRequest("US12345", InstrumentType.BOND, Region.AMRS, "BBG");
        ir2 = new InstrumentRequest("US1555", InstrumentType.CDS, Region.AMRS, "twB");
        ir3 = new InstrumentRequest("US123", InstrumentType.STOCKS, Region.AMRS, "MKA");
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
        logger.info("--running addNewBookTest--");
        InstrumentRequest ir4 = new InstrumentRequest("US15", InstrumentType.BOND, Region.AMRS, "BBG");
        Instrument instrument = orderBookImpl.addInstrument(ir4);
        Assert.assertEquals(instrument.getInstrumentId(), ir4.getInstrumentId());
        Assert.assertEquals(instrument.getInstrumentType(), ir4.getInstrumentType());
    }

    @Test(groups = {"OrderBookTests"})
    public void openInstrumentBookTest() {
        logger.info("--running openInstrumentBookTest--");
        BookStatus bookStatus = orderBookImpl.openInstrumentBook("US12345");
        Assert.assertEquals(bookStatus, BookStatus.BOOK_OPENED);
    }

    @Test(groups = {"OrderBookTests"})
    public void closeInstrumentBookTest() {
        logger.info("--running closeInstrumentBookTest--");
        //For closing a book before it should be in Open Status
        BookStatus bookStatus = orderBookImpl.openInstrumentBook("US12345");
        BookStatus bookStatus1 = orderBookImpl.closeInstrumentBook("US12345");
        Assert.assertEquals(bookStatus1, BookStatus.BOOK_CLOSED);
    }

    @Test(groups = {"OrderBookTests"})
    public void addOrderForInstrument() {
        logger.info("--running addOrderForInstrument--");
        order1 = new Order(12d, new Date(),ir2, 1000d, Side.ASK, OrderType.LIMIT_ORDER );
        OrderStatus orderStatus = orderBookImpl.addOrderForInstrument(order1);
        Assert.assertEquals(orderStatus, OrderStatus.BOOK_NOT_OPENED);

        masterBook.get("US1555").getOrderBook().openBoook();
        order1 = new Order(12d, new Date(),ir2, 1000d, Side.ASK, OrderType.LIMIT_ORDER );
        OrderStatus orderStatus1 = orderBookImpl.addOrderForInstrument(order1);
        Assert.assertEquals(orderStatus1, OrderStatus.ORDER_ADDED);
    }

    @Test(groups = {"OrderBookTests"})
    public void addExecutionTest() {
        logger.info("--running addOrderForInstrument--");
        ExecutionRequest executionRequest = new ExecutionRequest("US123", 150, 1000d);
        OrderStatus orderStatus = orderBookImpl.addExecution(executionRequest);
        Assert.assertEquals(orderStatus, OrderStatus.EXECUTION_ADDED);
    }

}
