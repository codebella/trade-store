package com.sample.services.trade.capture.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sample.trade.TradeModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/trades/capture")
public class TradeController {

    @Value(value = "${kafka.topic}")
    private String topic;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @PostMapping("/trade")
    @ApiModelProperty()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 202, message = "Accepted")})
    @HystrixCommand(fallbackMethod = "defaultRequest")
    public ResponseEntity postTrade(@RequestBody TradeModel view) {
        Message<TradeModel> message = MessageBuilder.withPayload(view).build();
        kafkaTemplate.send(topic, message);
        ResponseEntity response = ResponseEntity.accepted().build();
        return response;
    }

    @PutMapping("/trade")
    public ResponseEntity putTrade(@RequestBody TradeModel view) {
        Message<TradeModel> message = MessageBuilder.withPayload(view).build();
        kafkaTemplate.send(topic, message);
        ResponseEntity response = ResponseEntity.accepted().build();

        return response;
    }

    @DeleteMapping("/dtrade/{id}?{version}")
    public ResponseEntity delete(@PathParam("id") String id, @QueryParam("version") Long version) {
        /*Message<TradeModel> message = MessageBuilder.withPayload(view).build();
        kafkaTemplate.send(topic, message);*/
        ResponseEntity response = ResponseEntity.accepted().build();

        return response;
    }

    public ResponseEntity defaultRequest(@RequestBody TradeModel view) {
        ResponseEntity response = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();

        return response;
    }

}
