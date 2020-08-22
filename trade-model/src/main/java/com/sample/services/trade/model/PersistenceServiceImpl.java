package com.sample.services.trade.model;

import com.sample.trade.Status;
import com.sample.trade.TradeModel;
import com.sample.trade.TradeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
public class PersistenceServiceImpl implements PersistenceService {
    private final static Logger LOG = LoggerFactory.getLogger(PersistenceServiceImpl.class);
    @Autowired
    private TradeRepository repository;

    @Transactional
    public TradeResponse save(TradeModel Trade) {
        LOG.debug("Saving the trade");
        Key key = new Key(Trade.getTradeId(), Trade.getVersion());

        Status status = Status.Created;
        boolean existingEntry = entityExists(key);
        if (existingEntry) {
            LOG.warn("Trade already exists");
            status = Status.AlreadyExists;
        }
        TradeEntity entity = new TradeEntity();
        BeanUtils.copyProperties(Trade, entity);
        entity.setKey(key);

        TradeEntity savedEntity = repository.save(entity);
        TradeResponse resultResult = new TradeResponse(savedEntity.getKey().getTradeId(), savedEntity.getKey().getVersion(), status);
        return resultResult;
    }

    @Transactional
    public TradeResponse saveOrUpdate(TradeModel Trade) {
        Key key = new Key(Trade.getTradeId(), Trade.getVersion());

        boolean existingEntry = entityExists(key);
        TradeEntity entity = new TradeEntity();
        BeanUtils.copyProperties(Trade, entity);
        entity.setKey(key);

        repository.save(entity);
        TradeResponse resultResult = new TradeResponse(key.getTradeId(), key.getVersion(), Status.Updated);
        if (!existingEntry) {
            resultResult = new TradeResponse(key.getTradeId(), key.getVersion(), Status.Created);
        }
        return resultResult;
    }

    private TradeModel query(String tradeId, Long version) {
        Key key = new Key(tradeId, version);
        TradeEntity entity = repository.findById(key).orElse(null);
        TradeModel Trade = null;
        if (entity != null) {
            Trade = new TradeModel();
            BeanUtils.copyProperties(entity, Trade);
            Trade.setTradeId(tradeId);
            Trade.setVersion(version);
        }
        return Trade;
    }

    @Transactional
    public Integer mature(Date businessDt) {
        LOG.info("Maturing trades for business Date: {}", businessDt);
        return repository.mature(businessDt);
    }

    private boolean entityExists(Key key) {
        List<TradeEntity> tradeEntities = repository.findByKeyTradeIdAndKeyVersionGreaterThan(key.getTradeId(), key.getVersion());
        boolean exists = !tradeEntities.isEmpty();
        if (exists) {
            LOG.info("A higher version trade exists : {}", key.getTradeId());
        } else {
            TradeEntity existingEntry = repository.findById(key).orElse(null);
            exists = existingEntry != null;
        }
        return exists;
    }
}
