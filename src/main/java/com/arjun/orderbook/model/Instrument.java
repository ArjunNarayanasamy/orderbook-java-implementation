package com.arjun.orderbook.model;

import com.arjun.orderbook.service.BaseOrderBook;

public class Instrument {

    private String instrumentId;
    private InstrumentType instrumentType;
    private Region region;
    private String sourceSystem;
    private BaseOrderBook orderBook;

    public Instrument(String instrumentId, InstrumentType instrumentType) {
        this.instrumentId = instrumentId;
        this.instrumentType = instrumentType;
        orderBook = new BaseOrderBook();
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public BaseOrderBook getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(BaseOrderBook orderBook) {
        this.orderBook = orderBook;
    }
}
