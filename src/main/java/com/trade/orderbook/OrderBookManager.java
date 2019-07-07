package com.trade.orderbook;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderBookManager {

    Map<String, OrderBook> masterBook = new ConcurrentHashMap<>();

    public boolean addNewBook(String instrumentId) {
        if (!masterBook.containsKey(instrumentId)) {
            masterBook.put(instrumentId, new OrderBook());
            return true;
        } else {
            return false;
        }
    }

    public String openOrderBookForInstrument(String instrumentId) {
        if (!masterBook.containsKey(instrumentId)) {
            return "OrderBook doesn't exists!";
        } else {
            if (masterBook.get(instrumentId).openBook())
                return "Book opened!";
            else
                return "Book is already in OPEN state";
        }
    }

    public String closeOrderBookForInstrument(String instrumentId) {
        if (!masterBook.containsKey(instrumentId)) {
            return "OrderBook doesn't exists!";
        } else {
            if (masterBook.get(instrumentId).closeBook())
                return "Book Closed!";
            else
                return "Book is already in CLOSED state";
        }
    }

    public String addOrderForInstrument(Order order) {
        if (!masterBook.containsKey(order.getInstrumentId())) {
            return "Orderbook for specified Instrument is not present.";
        } else {
            System.out.println(order.toString());
            return masterBook.get(order.getInstrumentId()).addOrder(order);
        }
    }

    public List<Order> getOrdersForInstrument(String instrumentId) {
        if (!masterBook.containsKey(instrumentId)) {
            return null;
        } else {
            List<Order> orders = masterBook.get(instrumentId).getOrderBook();
            for (Order order : orders) {
                System.out.println(">> "+order.toString());
            }
            return orders;
        }
    }

    public Map<String, OrderBook> getAllBooks() {
        return masterBook;
    }


}
