package com.boa.orderbook.model;

public enum OrderStatus {

    ORDER_ADDED("Order is added to InstrumentBook"),
    ORDER_NOT_ADDED("Couldn't add order to InstrumentBook"),
    BOOK_NOT_OPENED("Orderbook for this instrument in closed status, couldn't add order"),
    BOOK_NOT_EXISTS("BaseOrderBook for the instrument not exists");

    String status;

    OrderStatus(String status){
        this.status = status;
    }
}
