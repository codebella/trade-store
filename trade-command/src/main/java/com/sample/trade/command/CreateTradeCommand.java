package com.sample.trade.command;

import com.sample.services.trade.model.PersistenceService;
import com.sample.trade.TradeModel;
import com.sample.trade.TradeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTradeCommand implements Command<TradeModel, TradeResponse> {
    private final static Logger LOG = LoggerFactory.getLogger(CreateTradeCommand.class);
    @Autowired
    private PersistenceService service;

    public TradeResponse execute(TradeModel view) {
        LOG.info("Processing trade with id: {}, version: {}", view.getTradeId(), view.getVersion());
        TradeResponse result = service.save(view);

        return result;
    }

}
