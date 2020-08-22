package com.sample.services.trade.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "Trade")
public class TradeEntity {
    @EmbeddedId
    private Key key;
    @Column
    private String cptyId;
    private String bookId;
    private Date maturityDt;
    private Date createdDt;
    private String expired;

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

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getExpired() {
        return expired;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}

