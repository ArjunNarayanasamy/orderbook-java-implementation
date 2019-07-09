package com.boa.orderbook.controller;

import com.boa.orderbook.model.Instrument;
import com.boa.orderbook.model.Order;
import com.boa.orderbook.service.OrderBook;
import com.boa.orderbook.service.OrderBookManager;
import com.boa.orderbook.service.RequestStatus;
import com.boa.orderbook.service.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderBookController {

    @Autowired
    OrderBookManager orderBookManager;

    @PostMapping(path = "/openBook", consumes = "application/json", produces = "application/json")
    public RequestStatus openOrderBook(@RequestBody Instrument instrument) {
        return buildResponse(instrument.getInstrumentId(),
                orderBookManager.openOrderBookForInstrument(instrument.getInstrumentId()),
                RequestType.OPEN_BOOK);
    }

    @PostMapping(path = "/closeBook", consumes = "application/json", produces = "application/json")
    public RequestStatus closeOrderBook(@RequestBody Instrument instrument) {
        return buildResponse(instrument.getInstrumentId(),
                orderBookManager.closeOrderBookForInstrument(instrument.getInstrumentId()),
                RequestType.CLOSE_BOOK);
    }

    @PostMapping(path = "/addOrder", consumes = "application/json", produces = "application/json")
    public RequestStatus addOrder(@RequestBody Order order) {
        return buildResponse(order.getInstrument().getInstrumentId(),
                orderBookManager.addOrderForInstrument(order), RequestType.ADD_ORDER);
    }

    @PostMapping(path = "/addBook", consumes = "application/json", produces = "application/json")
    public RequestStatus addOrderBook(@RequestBody Instrument instrument) {
        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setInstrumentId(instrument.getInstrumentId());
        if(orderBookManager.addNewBook(instrument.getInstrumentId())) {
            requestStatus.setStatus("Book added");
        } else {
            requestStatus.setStatus("Book already exists");
        }
        requestStatus.setRequestType(RequestType.ADD_BOOK);
        return requestStatus;
    }

    @PostMapping(path = "/getOrders", consumes = "application/json", produces = "application/json")
    public List<Order> getOrderListForInstrument(@RequestBody Instrument instrument) {
        return orderBookManager.getOrdersForInstrument(instrument.getInstrumentId());
    }

    @GetMapping(path = "/getAllBooks", produces = "application/json")
    public Map<String, OrderBook> getAllBooks() {
        return orderBookManager.getAllBooks();
    }

    public RequestStatus buildResponse(String instrumentId, String status, RequestType requestType) {
        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setInstrumentId(instrumentId);
        requestStatus.setRequestType(requestType);
        requestStatus.setStatus(status);
        return requestStatus;
    }


}
