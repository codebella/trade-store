package com.sample.trade;

import java.util.Date;

public interface PersistenceService {
    TradeResponse save(TradeRequest tradeRequest);

    TradeResponse saveOrUpdate(TradeRequest tradeRequest);

    Integer mature(Date businessDt);

}
