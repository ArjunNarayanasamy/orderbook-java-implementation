package com.boa.orderbook.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.boa.orderbook.service.OrderType;
import com.boa.orderbook.service.Side;

import java.util.Date;

/**
 * Orders are immutable
 */
public final class Order {
    private final Double quantity;
    private final Date entryDate;
    private final Instrument instrument;
    private final Double price;
    private final Side side;
    private final OrderType orderType;

    @JsonCreator
    public Order(@JsonProperty("quantity") Double quantity, @JsonProperty("entryDate") Date entryDate,
                 @JsonProperty("instrument") Instrument instrument,
                 @JsonProperty("price") Double price, @JsonProperty("side") Side side,
                 @JsonProperty("orderType") OrderType orderType) {
        this.quantity = quantity;
        this.entryDate = entryDate;
        this.instrument = instrument;
        this.price = price;
        this.side = side;
        this.orderType = orderType;
    }

    /**
     * Overloading constructor for Market_Order
     * User won't specify price in case of Market_Order
     * and will be looking for best available price
     */
    public Order(Double quantity, Date entryDate,
                 Instrument instrument,
                 Side side, OrderType orderType) {
        this.quantity = quantity;
        this.entryDate = entryDate;
        this.instrument = instrument;
        this.price = -9999999.99;
        this.side = side;
        this.orderType = orderType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public Double getPrice() {
        return price;
    }

    public Side getSide() {
        return side;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "quantity=" + quantity +
                ", entryDate=" + entryDate +
                ", instrument=" + instrument +
                ", price=" + price +
                ", side=" + side +
                ", orderType=" + orderType +
                '}';
    }
}
