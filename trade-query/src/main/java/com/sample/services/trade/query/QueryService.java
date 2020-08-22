package com.sample.services.trade.query;

import com.sample.trade.TradeModel;
import com.sample.trade.TradeResponse;

import java.util.List;

public interface QueryService {
    TradeModel queryLatestTrade(String tradeId);

    TradeModel queryTradeAndVersion(String tradeId, Long version);

    List<TradeModel> queryTradeVersions(String tradeId);

}
