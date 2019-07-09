package com.arjun.orderbook.service;

import com.arjun.orderbook.model.*;
import com.arjun.orderbook.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderBookImpl implements OrderBook {

    Logger logger = LoggerFactory.getLogger(OrderBookImpl.class);
    Map<String, Instrument> masterBook = new ConcurrentHashMap<>();

    public void setMasterBook(Map<String, Instrument> masterBook) {
        this.masterBook = masterBook;
    }

    @Override
    public OrderStatus addOrderForInstrument(Order order) {
        String instrumentId = order.getInstrument().getInstrumentId();
        if (!masterBook.containsKey(instrumentId)) {
            return OrderStatus.BOOK_NOT_EXISTS;
        } else {
            BaseOrderBook orderBook = masterBook.get(instrumentId).getOrderBook();
            if (orderBook.isOpen()) {
                logger.info("The book for {} is open, adding order with price {}:{}", instrumentId,
                        order.getSide(), order.getPrice());
                if(orderBook.addOrder(order)) {
                    return OrderStatus.ORDER_ADDED;
                } else {
                    return OrderStatus.ORDER_NOT_ADDED;
                }
            } else {
                logger.warn("BaseOrderBook for {} - not opened. Couldn't add order {}:{}", instrumentId,
                        order.getSide(), order.getPrice());
                return OrderStatus.BOOK_NOT_OPENED;
            }
        }
    }

    @Override
    public List<Order> getOrders(String instrumentId) {
        if(!masterBook.containsKey(instrumentId)) {
            logger.warn("No orderbook found for instrument - {}", instrumentId);
            return new ArrayList<>();
        } else {
            return masterBook.get(instrumentId).getOrderBook().getOrders();
        }
    }

    @Override
    public boolean isInstrumentExist(String instrumentId) {
       return masterBook.containsKey(instrumentId);
    }

    @Override
    public Instrument addInstrument(InstrumentRequest instrumentRequest) {
        Instrument instrument = new Instrument(instrumentRequest.getInstrumentId(), instrumentRequest.getInstrumentType());
        instrument.setRegion(instrumentRequest.getRegion());
        instrument.setSourceSystem(instrumentRequest.getSourceSystem());
        masterBook.put(instrumentRequest.getInstrumentId(), instrument);
        return instrument;
    }

    @Override
    public BookStatus openInstrumentBook(String instrumentId) {
        BookStatus bookStatus;
        boolean isExist = isInstrumentExist(instrumentId);
        if(isExist) {
            bookStatus = masterBook.get(instrumentId).getOrderBook().openBoook();
        } else {
            bookStatus = BookStatus.BOOK_DOESNT_EXIST;
        }
        return bookStatus;
    }

    @Override
    public BookStatus closeInstrumentBook(String instrumentId) {
        BookStatus bookStatus;
        boolean isExist = isInstrumentExist(instrumentId);
        if (isExist) {
            bookStatus = masterBook.get(instrumentId).getOrderBook().closeBook();
        } else {
            bookStatus = BookStatus.BOOK_DOESNT_EXIST;
        }
        return bookStatus;
    }

    @Override
    public OrderStatus addExecution(ExecutionRequest executionRequest) {
        OrderStatus orderStatus;
        BaseOrderBook baseOrderBook;
        boolean isExist = isInstrumentExist(executionRequest.getInstrumentId());
        if(isExist) {
            baseOrderBook = masterBook.get(executionRequest.getInstrumentId()).getOrderBook();
            if(!baseOrderBook.isOpen()) {
                baseOrderBook.addExecution(executionRequest);
                orderStatus = OrderStatus.EXECUTION_ADDED;
            } else {
                orderStatus = OrderStatus.BOOK_NOT_CLOSED;
            }
        } else {
            orderStatus = OrderStatus.BOOK_NOT_EXISTS;
        }
        return orderStatus;
    }

}
