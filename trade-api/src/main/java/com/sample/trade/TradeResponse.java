package com.sample.trade;

import java.util.ArrayList;
import java.util.List;

public class TradeResponse {
    private String tradeId;
    private Long version;
    private Status responseStatus;
    private List<String> errorMessage;

    public TradeResponse() {

    }

    public TradeResponse(List<String> errorMessage, Status responseStatus) {
        this.errorMessage = errorMessage;
        this.responseStatus = responseStatus;
    }

    public TradeResponse(String tradeId, Long version, Status responseStatus) {
        this.tradeId = tradeId;
        this.version = version;
        this.responseStatus = responseStatus;
        errorMessage = new ArrayList<>();
    }

    public String getTradeId() {
        return tradeId;
    }

    public Long getVersion() {
        return version;
    }

    public Status getResponseStatus() {
        return responseStatus;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }
}
