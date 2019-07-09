package com.arjun.orderbook.service;

import com.arjun.orderbook.model.*;
import com.arjun.orderbook.model.*;

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
