package com.arjun.orderbook.model;

public enum BookStatus {
    BOOK_ALREADY_OPEN("BOOK_ALREADY_OPEN", "Book already in open status"),
    BOOK_ALREADY_CLOSED("BOOK_ALREADY_CLOSED", "Book already in closed status"),
    BOOK_DOESNT_EXIST("BOOK_DOESNT_EXIST", "Book doesn't exist"),
    BOOK_OPENED("BOOK_OPENED", "Book opened successfully"),
    BOOK_CLOSED("BOOK_CLOSED", "Book closed successfully");

    public String code, message;

    BookStatus(String code, String message){
        this.code = code;
        this.message = message;
    }
}
