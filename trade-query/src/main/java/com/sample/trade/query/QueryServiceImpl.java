package com.sample.trade.query;

import com.sample.trade.Key;
import com.sample.trade.TradeRequest;
import com.sample.trade.TradeEntity;
import com.sample.trade.TradeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryServiceImpl implements QueryService {

    @Autowired
    private TradeRepository repository;

    @Override
    public TradeRequest queryLatestTrade(String tradeId) {
        TradeEntity entity = repository.findFirstByKeyTradeIdOrderByKeyVersionDesc(tradeId);
        return convert(entity);
    }

    @Override
    public TradeRequest queryTradeAndVersion(String tradeId, Long version) {
        Key key = new Key(tradeId, version);
        TradeEntity entity = repository.findById(key).orElse(null);
        return convert(entity);
    }

    @Override
    public List<TradeRequest> queryTradeVersions(String tradeId) {
        List<TradeEntity> tradeEntites = repository.findByKeyTradeId(tradeId);
        List<TradeRequest> tradeRequests = new ArrayList<>();
        if (tradeEntites != null) {
            for (TradeEntity entity : tradeEntites) {
                tradeRequests.add(convert(entity));
            }
        }
        return tradeRequests;
    }


    private TradeRequest convert(TradeEntity entity) {
        TradeRequest tradeRequest = null;
        if (entity != null) {
            tradeRequest = new TradeRequest();
            BeanUtils.copyProperties(entity, tradeRequest);
            tradeRequest.setTradeId(entity.getKey().getTradeId());
            tradeRequest.setVersion(entity.getKey().getVersion());
        }
        return tradeRequest;
    }
}
