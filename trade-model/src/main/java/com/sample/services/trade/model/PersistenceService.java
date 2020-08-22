package com.sample.services.trade.model;

import com.sample.trade.TradeModel;
import com.sample.trade.TradeResponse;

import java.util.Date;

public interface PersistenceService {
    TradeResponse save(TradeModel tradeModel);

    TradeResponse saveOrUpdate(TradeModel tradeModel);

    Integer mature(Date businessDt);

}
