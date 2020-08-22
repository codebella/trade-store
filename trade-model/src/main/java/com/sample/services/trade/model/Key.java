package com.sample.services.trade.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Key implements Serializable {
    private String tradeId;

    private Long version;

    public Key() {

    }

    public Key(String tradeId, Long version) {
        this.tradeId = tradeId;
        this.version = version;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return tradeId.equals(key.tradeId) &&
                version.equals(key.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, version);
    }
}
