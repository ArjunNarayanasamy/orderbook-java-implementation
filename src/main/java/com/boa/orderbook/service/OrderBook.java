package com.boa.orderbook.service;

import com.boa.orderbook.model.*;

import java.util.List;

public interface OrderBook {

    boolean isInstrumentExist(String instrumentId);
    Instrument addInstrument(InstrumentRequest instrumentRequest);
    BookStatus openInstrumentBook(String instrumentId);
    BookStatus closeInstrumentBook(String instrumentId);
    OrderStatus addOrderForInstrument(Order order);
    List<Order> getOrders(String instrumentId);
    OrderStatus addExecution(ExecutionRequest executionRequest);
}
