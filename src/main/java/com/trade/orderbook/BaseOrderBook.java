package com.trade.orderbook;

public interface BaseOrderBook {
    public boolean openBook();
    public boolean closeBook();
    public String addOrder(String instrumentId, Order order);
    //public String addExecution()
}
