package com.sample.services.trade.capture.controller;

import com.sample.trade.TradeModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trades/capture")
public class TradeController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @PostMapping("/trade")
    @ApiModelProperty()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 202, message = "Accepted")})
    public ResponseEntity postTrade(@RequestBody TradeModel view) {
        Message<TradeModel> message = MessageBuilder.withPayload(view).build();
        kafkaTemplate.send(message);
        ResponseEntity response = ResponseEntity.accepted().build();

        return response;
    }

    @PutMapping("/trade")
    public ResponseEntity putTrade(@RequestBody TradeModel view) {
        Message<TradeModel> message = MessageBuilder.withPayload(view).build();
        kafkaTemplate.send(message);
        ResponseEntity response = ResponseEntity.accepted().build();

        return response;
    }

}c
