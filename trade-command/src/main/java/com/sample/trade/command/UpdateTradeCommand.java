package com.sample.trade.command;

import com.sample.trade.PersistenceService;
import com.sample.trade.TradeRequest;
import com.sample.trade.TradeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateTradeCommand implements Command<TradeRequest, TradeResponse> {
    private final static Logger LOG = LoggerFactory.getLogger(UpdateTradeCommand.class);

    @Autowired
    private PersistenceService service;

    public TradeResponse execute(TradeRequest request) {
        LOG.info("Processing trade with id: {}, version: {}", request.getTradeId(), request.getVersion());
        TradeResponse result = service.save(request);
        return result;
    }

}
