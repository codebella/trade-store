package com.sample.trade;

import java.util.Date;

public class TradeModel {
    private String tradeId;
    private Long version;
    private String cptyId;
    private String bookId;
    private Date maturityDt;
    private Date createdDt;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCptyId() {
        return cptyId;
    }

    public void setCptyId(String cptyId) {
        this.cptyId = cptyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getMaturityDt() {
        return maturityDt;
    }

    public void setMaturityDt(Date maturityDt) {
        this.maturityDt = maturityDt;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }
}
