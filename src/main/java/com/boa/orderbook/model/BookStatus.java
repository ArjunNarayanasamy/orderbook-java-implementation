package com.boa.orderbook.model;

public enum BookStatus {
    BOOK_ALREADY_OPEN("Book already in open status"),
    BOOK_ALREADY_CLOSED("Book already in closed status"),
    BOOK_DOESNT_EXIST("Book doesn't exist"),
    BOOK_OPENED("Book opened successfully"),
    BOOK_CLOSED("Book closed successfully");

    String status;

    BookStatus(String status){
        this.status = status;
    }
}
