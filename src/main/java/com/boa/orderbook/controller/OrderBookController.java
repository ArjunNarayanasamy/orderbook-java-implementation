package com.boa.orderbook.controller;

import com.boa.orderbook.model.*;
import com.boa.orderbook.model.response.OrderListResponse;
import com.boa.orderbook.service.OrderBookImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderBookController {

    @Autowired
    OrderBookImpl orderBookImpl;

    @GetMapping("/openBook")
    public ResponseEntity openOrderBook(@RequestParam(required = true) String instrumentId) {
        BookStatus bookStatus = orderBookImpl.openInstrumentBook(instrumentId);
        ResponseEntity<BookStatus> responseEntity;
        if(bookStatus.equals(BookStatus.BOOK_OPENED)) {
            responseEntity = new ResponseEntity<>(bookStatus, HttpStatus.ACCEPTED);
        } else {
            responseEntity = new ResponseEntity<>(bookStatus, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/closeBook")
    public ResponseEntity closeOrderBook(@RequestParam(required = true) String instrumentId) {
        BookStatus bookStatus = orderBookImpl.closeInstrumentBook(instrumentId);
        ResponseEntity<BookStatus> responseEntity;
        if(bookStatus.equals(BookStatus.BOOK_CLOSED)) {
            responseEntity = new ResponseEntity<>(bookStatus, HttpStatus.ACCEPTED);
        } else {
            responseEntity = new ResponseEntity<>(bookStatus, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/addOrder")
    public ResponseEntity addOrder(@RequestBody Order order) {
        OrderStatus orderStatus = orderBookImpl.addOrderForInstrument(order);
        ResponseEntity<OrderStatus> responseEntity;
        if(orderStatus.equals(OrderStatus.ORDER_ADDED)) {
            responseEntity = new ResponseEntity<OrderStatus>(orderStatus, HttpStatus.ACCEPTED);
        } else {
            responseEntity = new ResponseEntity<OrderStatus>(orderStatus, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping(path = "/addBook", consumes = "application/json")
    public ResponseEntity addOrderBook(@RequestBody InstrumentRequest instrumentRequest) {
        ResponseEntity<Map> responseEntity;
        Map<String, String> response = new HashMap<>();
        boolean isExist = orderBookImpl.isInstrumentExist(instrumentRequest.getInstrumentId());
        if(isExist) {
            response.put("message", "Book already exists for " + instrumentRequest.getInstrumentId());
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            Instrument instrument = orderBookImpl.addInstrument(instrumentRequest);
            response.put("message", "Book Added for " + instrument.getInstrumentId());
            responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @PostMapping("/getOrders")
    public ResponseEntity getOrderListForInstrument(@RequestBody InstrumentRequest instrumentRequest) {
        List<Order> orders = orderBookImpl.getOrders(instrumentRequest.getInstrumentId());
        OrderListResponse response = new OrderListResponse(instrumentRequest.getInstrumentId(), orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
