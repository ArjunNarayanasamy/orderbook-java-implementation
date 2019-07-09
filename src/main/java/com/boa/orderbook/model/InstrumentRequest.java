package com.boa.orderbook.model;

public class InstrumentRequest {
     private String instrumentId;
     private InstrumentType instrumentType;
     private Region region;
     private String sourceSystem;

     public InstrumentRequest() {
     }

     public InstrumentRequest(String instrumentId, InstrumentType instrumentType, Region region, String sourceSystem) {
          this.instrumentId = instrumentId;
          this.instrumentType = instrumentType;
          this.region = region;
          this.sourceSystem = sourceSystem;
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
}
