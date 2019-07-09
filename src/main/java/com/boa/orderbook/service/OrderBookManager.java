package com.boa.orderbook.service;

import com.boa.orderbook.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderBookManager {

    Logger logger = LoggerFactory.getLogger(OrderBookManager.class);
    Map<String, OrderBook> masterBook = new ConcurrentHashMap<>();

    public boolean addNewBook(String instrumentId) {
        if (!masterBook.containsKey(instrumentId)) {
            masterBook.put(instrumentId, new OrderBook());
            logger.info("Added new book for instrument - {}", instrumentId);
            return true;
        } else {
            logger.info("The book for instrument {} already present", instrumentId);
            return false;
        }
    }

    public String openOrderBookForInstrument(String instrumentId) {
        if (!masterBook.containsKey(instrumentId)) {
            logger.info("Couldn't open book - No book available for {}", instrumentId);
            return Constants.BOOK_NOT_EXIST;
        } else {
            if (masterBook.get(instrumentId).openBook())
                return Constants.BOOK_OPENED;
            else
                return Constants.BOOK_ALREADY_OPENED;
        }
    }

    public String closeOrderBookForInstrument(String instrumentId) {
        if (!masterBook.containsKey(instrumentId)) {
            logger.info("Couldn't close book - No book available for {}", instrumentId);
            return Constants.BOOK_NOT_EXIST;
        } else {
            if (masterBook.get(instrumentId).closeBook())
                return Constants.BOOK_CLOSED;
            else
                return Constants.BOOK_ALREADY_CLOSED;
        }
    }

    public String addOrderForInstrument(Order order) {
        String instrumentId = order.getInstrument().getInstrumentId();
        if (!masterBook.containsKey(instrumentId)) {
            return Constants.BOOK_NOT_PRESENT;
        } else {
            OrderBook orderBook = masterBook.get(instrumentId);
            if (orderBook.isOpen()) {
                logger.info("The book for {} is open, adding order with price {}:{}", instrumentId,
                        order.getSide(), order.getPrice());
                return orderBook.addOrder(order);
            } else {
                logger.warn("OrderBook for {} - not opened. Couldn't add order {}:{}", instrumentId,
                        order.getSide(), order.getPrice());
                return Constants.BOOK_NOT_OPENED;
            }
        }
    }

    public List<Order> getOrdersForInstrument(String instrumentId) {
        if (!masterBook.containsKey(instrumentId)) {
            logger.warn("No orderbook found for instrument - {}", instrumentId);
            return null;
        } else {
            return masterBook.get(instrumentId).getOrderBook();
        }
    }

    public Map<String, OrderBook> getAllBooks() {
        return masterBook;
    }


}
