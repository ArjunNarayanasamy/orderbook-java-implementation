package com.trade.orderbook;

public interface BaseOrderBook {
    public boolean openBook();
    public boolean closeBook();
    public String addOrder(Order order);
    //public String addExecution()
}
