package com.sample.services.trade.query;

import com.sample.services.trade.model.Key;
import com.sample.trade.TradeModel;
import com.sample.services.trade.model.TradeEntity;
import com.sample.services.trade.model.TradeRepository;
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
    public TradeModel queryLatestTrade(String tradeId) {
        TradeEntity entity = repository.findFirstByKeyTradeIdOrderByKeyVersionDesc(tradeId);
        return convert(entity);
    }

    @Override
    public TradeModel queryTradeAndVersion(String tradeId, Long version) {
        Key key = new Key(tradeId, version);
        TradeEntity entity = repository.findById(key).orElse(null);
        return convert(entity);
    }

    @Override
    public List<TradeModel> queryTradeVersions(String tradeId) {
        List<TradeEntity> tradeEntites = repository.findByKeyTradeId(tradeId);
        List<TradeModel> tradeModels = new ArrayList<>();
        if (tradeEntites != null) {
            for (TradeEntity entity : tradeEntites) {
                tradeModels.add(convert(entity));
            }
        }
        return tradeModels;
    }


    private TradeModel convert(TradeEntity entity) {
        TradeModel tradeModel = null;
        if (entity != null) {
            tradeModel = new TradeModel();
            BeanUtils.copyProperties(entity, tradeModel);
            tradeModel.setTradeId(entity.getKey().getTradeId());
            tradeModel.setVersion(entity.getKey().getVersion());
        }
        return tradeModel;
    }
}
