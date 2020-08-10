package com.sample.trade.query;

import com.sample.trade.TradeRequest;

import java.util.List;

public interface QueryService {
    TradeRequest queryLatestTrade(String tradeId);

    TradeRequest queryTradeAndVersion(String tradeId, Long version);

    List<TradeRequest> queryTradeVersions(String tradeId);

}
