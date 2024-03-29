package com.arjun.orderbook.model;

public class ExecutionRequest {
    private String instrumentId;
    private Integer quantity;
    private Double price;

    public ExecutionRequest() {
    }

    public ExecutionRequest(String instrumentId, Integer quantity, Double price) {
        this.instrumentId = instrumentId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
