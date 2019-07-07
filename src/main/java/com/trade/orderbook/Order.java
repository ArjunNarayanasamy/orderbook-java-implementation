package com.trade.orderbook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Orders are immutable
 */
public final class Order {
    private final Double quantity;
    private final Date entryDate;
    private final String instrumentId;
    private final Double price;
    private final boolean side;
    private final OrderType orderType;

    @JsonCreator
    public Order(@JsonProperty("quantity") Double quantity, @JsonProperty("entryDate") Date entryDate,
                 @JsonProperty("instrumentId") String instrumentId,
                 @JsonProperty("price") Double price, @JsonProperty("side") boolean side,
                 @JsonProperty("orderType") OrderType orderType) {
        this.quantity = quantity;
        this.entryDate = entryDate;
        this.instrumentId = instrumentId;
        this.price = price;
        this.side = side;
        this.orderType = orderType;
    }

    /**
     * Overloading constructor for Market_Order
     * User won't specify price in case of Market_Order
     * and will be looking for best available price
     */
    public Order(Double quantity, Date entryDate, String instrumentId,
                 boolean side, OrderType orderType) {
        this.quantity = quantity;
        this.entryDate = entryDate;
        this.instrumentId = instrumentId;
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

    public String getInstrumentId() {
        return instrumentId;
    }

    public Double getPrice() {
        return price;
    }

    public boolean isSide() {
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
                ", instrumentId='" + instrumentId + '\'' +
                ", price=" + price +
                ", side=" + side +
                ", orderType=" + orderType +
                '}';
    }
}
