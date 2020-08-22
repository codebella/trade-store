package com.sample.services.trade.query.controller;

import com.sample.services.trade.query.QueryService;
import com.sample.trade.TradeModel;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trades/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/trade/{tradeId}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Trade not found"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = TradeModel.class)})
    public ResponseEntity readTrade(@PathVariable String tradeId, @RequestParam Long version) {
        ResponseEntity response;

        TradeModel Trade = queryService.queryTradeAndVersion(tradeId, version);
        if (Trade != null) {
            response = ResponseEntity.ok(Trade);
        } else {
            response = ResponseEntity.accepted().build();
        }
        return response;
    }

/*    @PatchMapping("/mature")
    public ResponseEntity doMature() {
        Message<TradeRequest> message = MessageBuilder.withPayload(view).build();
        kafkaTemplate.send(message);
        ResponseEntity response = ResponseEntity.accepted().build();
        return response;
    }*/

}
