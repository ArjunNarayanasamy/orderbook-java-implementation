package com.arjun.orderbook.service;

import com.arjun.orderbook.model.BookStatus;
import com.arjun.orderbook.model.ExecutionRequest;
import com.arjun.orderbook.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BaseOrderBook {

    List<Order> orders = new ArrayList<>();
    List<ExecutionRequest> executions = new ArrayList<>();
    AtomicBoolean openFlag = new AtomicBoolean(false);


    public BookStatus openBoook() {
        return openFlag.compareAndSet(false, true)
                ? BookStatus.BOOK_OPENED : BookStatus.BOOK_ALREADY_OPEN;
    }

    public BookStatus closeBook() {
        return openFlag.compareAndSet(true, false)
                ? BookStatus.BOOK_CLOSED : BookStatus.BOOK_ALREADY_CLOSED;
    }

    public boolean isOpen() {
        return openFlag.get() ? true : false;
    }

    public boolean addOrder(Order order) {
        orders.add(order);
        return true;
    }

    public boolean addExecution(ExecutionRequest executionRequest) {
        executions.add(executionRequest);
        return true;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
