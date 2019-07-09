package com.arjun.orderbook.controller;

import com.arjun.orderbook.model.*;
import com.arjun.orderbook.service.OrderBookImpl;
import com.arjun.orderbook.model.*;
import com.arjun.orderbook.model.response.OrderListResponse;
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
            responseEntity = generateBookReponse(bookStatus, HttpStatus.ACCEPTED);
        } else {
            responseEntity = generateBookReponse(bookStatus, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/closeBook")
    public ResponseEntity closeOrderBook(@RequestParam(required = true) String instrumentId) {
        BookStatus bookStatus = orderBookImpl.closeInstrumentBook(instrumentId);
        ResponseEntity<BookStatus> responseEntity;
        if(bookStatus.equals(BookStatus.BOOK_CLOSED)) {
            responseEntity = generateBookReponse(bookStatus, HttpStatus.ACCEPTED);
        } else {
            responseEntity = generateBookReponse(bookStatus, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/addOrder")
    public ResponseEntity addOrder(@RequestBody Order order) {
        OrderStatus orderStatus = orderBookImpl.addOrderForInstrument(order);
        ResponseEntity<OrderStatus> responseEntity;
        if(orderStatus.equals(OrderStatus.ORDER_ADDED)) {
            responseEntity = generateOrderResponse(orderStatus, HttpStatus.ACCEPTED);
        } else {
            responseEntity = generateOrderResponse(orderStatus, HttpStatus.BAD_REQUEST);
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

    @PostMapping("/addExecution")
    public ResponseEntity addExecutionsForInstrument(@RequestBody ExecutionRequest executionRequest) {
        OrderStatus orderStatus = orderBookImpl.addExecution(executionRequest);
        ResponseEntity<OrderStatus> responseEntity;
        if(orderStatus.equals(OrderStatus.EXECUTION_ADDED)) {
            responseEntity = generateOrderResponse(orderStatus, HttpStatus.OK);
        } else {
            responseEntity = generateOrderResponse(orderStatus, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    private ResponseEntity generateOrderResponse(OrderStatus orderStatus, HttpStatus httpStatus) {
        Map<String, String> response = new HashMap<>();
        response.put("code", orderStatus.code);
        response.put("message", orderStatus.message);
        return new ResponseEntity<>(response, httpStatus);
    }

    private ResponseEntity generateBookReponse(BookStatus bookStatus, HttpStatus httpStatus) {
        Map<String, String> response = new HashMap<>();
        response.put("code", bookStatus.code);
        response.put("message", bookStatus.message);
        return new ResponseEntity<>(response, httpStatus);
    }

}
