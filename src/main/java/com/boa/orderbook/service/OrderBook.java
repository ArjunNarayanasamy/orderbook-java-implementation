package com.boa.orderbook.service;

import com.boa.orderbook.model.Order;

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
    public boolean isOpen() {
        return openFlag.get() ? true : false;
    }

    @Override
    public String addOrder(Order order) {
        orders.add(order);
        return "Order added successfully";
    }

    @Override
    public List<Order> getOrderBook() {
        return orders;
    }
}