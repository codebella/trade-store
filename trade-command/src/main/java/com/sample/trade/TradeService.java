package com.sample.trade;

import java.time.Instant;
import java.util.Date;

public interface TradeService {
    TradeResponse save(TradeModel tradeModel);

    TradeResponse saveOrUpdate(TradeModel tradeModel);

    Integer mature();

    default Date getDate() {
        return Date.from(Instant.now());
    }
}
