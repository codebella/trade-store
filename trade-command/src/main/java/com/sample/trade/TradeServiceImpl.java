package com.sample.trade;

import com.sample.trade.command.CreateTradeCommand;
import com.sample.trade.command.MatureTradeCommand;
import com.sample.trade.command.UpdateTradeCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TradeServiceImpl implements TradeService {

    @Autowired
    private CreateTradeCommand createCommand;
    @Autowired
    private UpdateTradeCommand updateCommand;
    @Autowired
    private MatureTradeCommand matureCommand;

    public TradeResponse save(TradeRequest tradeRequest) {
        List<String> errorList = doValidate(tradeRequest);
        final TradeResponse response;
        if (errorList.isEmpty()) {
            response = createCommand.execute(tradeRequest);
        } else {
            response = new TradeResponse(errorList, Status.ValidationError);
        }
        return response;
    }

    public TradeResponse saveOrUpdate(TradeRequest tradeRequest) {
        List<String> errorList = doValidate(tradeRequest);
        final TradeResponse response;
        if (errorList.isEmpty()) {
            response = updateCommand.execute(tradeRequest);
        } else {
            response = new TradeResponse(errorList, Status.ValidationError);
        }
        return response;
    }

    public Integer mature() {
        return matureCommand.execute(getDate());
    }

    private List<String> doValidate(TradeRequest request) {
        List<String> errorList = new ArrayList<>();
        Date dateFromInstant = getDate();
        if (request.getMaturityDt().before(dateFromInstant)) {
            errorList.add("Maturity is in past");
        }
        if (request.getVersion() == 0) {
            errorList.add("Version is 0. Should start from 1");
        }
        return errorList;
    }
}
