package com.boa.orderbook.service;

import com.boa.orderbook.model.Order;

import java.util.List;

public interface BaseOrderBook {
    boolean openBook();
    boolean closeBook();
    boolean isOpen();
    String addOrder(Order order);
    List<Order> getOrderBook();
    //public String addExecution()
}
