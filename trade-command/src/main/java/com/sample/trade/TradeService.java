package com.sample.trade;

import java.time.Instant;
import java.util.Date;

public interface TradeService {
    TradeResponse save(TradeRequest tradeRequest);

    TradeResponse saveOrUpdate(TradeRequest tradeRequest);

    Integer mature();

    default Date getDate() {
        return Date.from(Instant.now());
    }
}
