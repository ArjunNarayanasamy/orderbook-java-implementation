package com.trade.orderbook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class OrderBook implements BaseOrderBook {

    List<Order> orders = new ArrayList<>();
    AtomicBoolean openFlag = new AtomicBoolean(false);

    @Override
    public boolean openBook() {
        return openFlag.compareAndSet(false, true) ? true : false;
    }

    @Override
    public boolean closeBook() {
        return openFlag.compareAndSet(true, false) ? true : false;
    }

    @Override
    public String addOrder(String instrumentId, Order order) {
        orders.add(order);
        return "Added successfully";
    }
}