package com.boa.orderbook.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

/**
 * Orders are immutable
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Order {
    private final Double quantity;
    private final Date entryDate;
    private final InstrumentRequest instrument;
    private final Double price;
    private final Side side;
    private final OrderType orderType;

    @JsonCreator
    public Order(@JsonProperty("quantity") Double quantity, @JsonProperty("entryDate") Date entryDate,
                 @JsonProperty("instrument") InstrumentRequest instrument,
                 @JsonProperty("price") Double price, @JsonProperty("side") Side side,
                 @JsonProperty("orderType") OrderType orderType) {
        this.quantity = quantity;
        this.entryDate = entryDate;
        this.instrument = instrument;
        this.price = price;
        this.side = side;
        this.orderType = orderType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public InstrumentRequest getInstrument() {
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
