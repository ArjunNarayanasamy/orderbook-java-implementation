package com.boa.orderbook.model;

public enum OrderStatus {

    ORDER_ADDED("ORDER_ADDED", "Order is added to InstrumentBook"),
    ORDER_NOT_ADDED("ORDER_NOT_ADDED", "Couldn't add order to InstrumentBook"),
    BOOK_NOT_OPENED("BOOK_NOT_OPENED", "Orderbook for this instrument in closed status, couldn't add order"),
    BOOK_NOT_EXISTS("BOOK_NOT_EXISTS", "BaseOrderBook for the instrument not exists"),
    EXECUTION_ADDED("EXECUTION_ADDED", "Execution added to InstrumentBook"),
    BOOK_NOT_CLOSED("BOOK_NOT_CLOSED", "Book not closed, so cannot add executions");


    public String code, message;

    OrderStatus(String code, String message){
        this.code = code;
        this.message = message;
    }
}
