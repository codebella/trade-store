package com.sample.trade.controller;

import com.sample.trade.Status;
import com.sample.trade.TradeRequest;
import com.sample.trade.TradeResponse;
import com.sample.trade.TradeService;
import com.sample.trade.query.QueryService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/trades")
public class TradeController {
    @Autowired
    private QueryService queryService;

    @Autowired
    private TradeService tradeService;

    @GetMapping("/trade/{tradeId}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Trade not found"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = TradeRequest.class)})
    public ResponseEntity readTrade(@PathVariable String tradeId, @RequestParam Long version) {
        ResponseEntity response;
        TradeRequest Trade = queryService.queryTradeAndVersion(tradeId, version);
        if (Trade != null) {
            response = ResponseEntity.ok(Trade);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @PostMapping("/trade")
    @ApiModelProperty()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error", response = TradeRequest.class),
            @ApiResponse(code = 422, message = "Duplicate Entry"),
            @ApiResponse(code = 201, message = "Created", response = TradeRequest.class)})
    public ResponseEntity postTrade(@RequestBody TradeRequest view) {
        ResponseEntity response;
        TradeResponse result = tradeService.save(view);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/trade/{tradeId}").build()
                .expand(result.getTradeId()).toUri();
        if (Status.Created == result.getResponseStatus()) {
            response = ResponseEntity.created(location).build();
        } else {
            response = ResponseEntity.unprocessableEntity().body(result);
        }

        return response;
    }

    @PutMapping("/trade")
    public ResponseEntity putTrade(@RequestBody TradeRequest view) {
        ResponseEntity response;
        TradeResponse result = tradeService.saveOrUpdate(view);
        if (Status.Updated == result.getResponseStatus()) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentServletMapping().path("/trade/{tradeId}").queryParam("version", result.getVersion()).build()
                    .expand(result.getTradeId(), result.getVersion()).toUri();
            response = ResponseEntity.ok(location);
        } else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentServletMapping().path("/trade/{tradeId}?{version}").build()
                    .expand(result.getTradeId(), result.getVersion()).toUri();
            response = ResponseEntity.created(location).build();
        }

        return response;
    }

    @PatchMapping("/mature")
    public ResponseEntity doMature() {
        Integer count = tradeService.mature();
        ResponseEntity response = ResponseEntity.ok("Entries Matured count: " + count);
        return response;
    }

}
