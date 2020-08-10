package com.sample.trade.command;

import com.sample.trade.PersistenceService;
import com.sample.trade.TradeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MatureTradeCommand implements Command<Date, Integer> {
    private final static Logger LOG = LoggerFactory.getLogger(MatureTradeCommand.class);

    @Autowired
    private PersistenceService service;

    public Integer execute(Date date) {
        LOG.info("Maturing the trades for business date: {}", date);

        Integer count = service.mature(date);

        return count;
    }

}
