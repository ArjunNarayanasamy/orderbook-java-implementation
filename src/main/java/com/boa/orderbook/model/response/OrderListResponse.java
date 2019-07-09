package com.boa.orderbook.model.response;

import com.boa.orderbook.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderListResponse {

    String instrumentId;

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    List<Order> orders;

    public OrderListResponse(String instrumentId) {
        new OrderListResponse(instrumentId, new ArrayList<>());
    }

    public OrderListResponse(String instrumentId, List<Order> orders) {
        this.instrumentId = instrumentId;
        this.orders = orders;
    }
}
