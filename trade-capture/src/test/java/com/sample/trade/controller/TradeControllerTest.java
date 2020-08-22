//package com.sample.trade.controller;
//
//import com.sample.trade.TradeRequest;
//import com.sample.trade.TradeResponse;
//import com.sample.trade.WebApplication;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Date;
//import java.util.List;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class TradeControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private String baseUrl;
//
//    @BeforeEach
//    public void setup() {
//        baseUrl = "http://localhost:" + port;
//    }
//
//    @Test
//    @Order(1)
//    void read_non_existing() {
//        String readUrl = baseUrl + "/trades/trade/T1?version=1";
//        Object response = this.restTemplate.getForObject(readUrl, Object.class);
//        Assertions.assertNull(response);
//    }
//
//    @Test
//    @Order(2)
//    void put_valid_non_existing_trade() {
//        String readUrl = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(5L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().plus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        this.restTemplate.put(readUrl, request);
//    }
//
//    @Test
//    @Order(3)
//    void post_new_trade() {
//        String readUrl = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(6L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().plus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        ResponseEntity<TradeResponse> response = this.restTemplate.postForEntity(readUrl, httpRequest, TradeResponse.class);
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(201, response.getStatusCodeValue());
//        Assertions.assertNotNull(response.getHeaders());
//        Assertions.assertNotNull(response.getHeaders().getLocation());
//
//    }
//
//
//    @Test
//    @Order(4)
//    void post_existing_trade() {
//        String readUrl = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(5L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().plus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        Object response = this.restTemplate.postForEntity(readUrl, httpRequest, Object.class);
//        System.out.println(response);
//        Assertions.assertNotNull(response);
//        //  Assertions.assertEquals(201, response.getStatusCodeValue());
//    }
//
//    @Test
//    @Order(5)
//    void put_existing_trade() {
//        String readUrl = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(5L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().plus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        this.restTemplate.put(readUrl, httpRequest, ResponseEntity.class);
//    }
//
//    @Test
//    @Order(6)
//    void patch_mature() {
//        String url = baseUrl + "/trades/mature";
//        TradeRequest request = new TradeRequest();
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("X-HTTP-Method-Override", List.of("PATCH"));
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        ResponseEntity response = this.restTemplate.postForEntity(url, httpRequest, TradeResponse.class);
//        Assertions.assertNotNull(response);
//    }
//
//    @Test
//    @Order(7)
//    void post_new_trade_with_version_0() {
//        String url = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(0L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().plus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        ResponseEntity<TradeResponse> response = this.restTemplate.postForEntity(url, httpRequest, TradeResponse.class);
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(422, response.getStatusCodeValue());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(1, response.getBody().getErrorMessage().size());
//        Assertions.assertTrue(response.getBody().getErrorMessage().contains("Version is 0. Should start from 1"));
//    }
//
//    @Test
//    @Order(8)
//    void post_new_trade_with_0_version_and_past_maturity() {
//        String readUrl = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(0L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().minus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        ResponseEntity<TradeResponse> response = this.restTemplate.postForEntity(readUrl, httpRequest, TradeResponse.class);
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(422, response.getStatusCodeValue());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(2, response.getBody().getErrorMessage().size());
//
//        Assertions.assertTrue(response.getBody().getErrorMessage().contains("Version is 0. Should start from 1"));
//        Assertions.assertTrue(response.getBody().getErrorMessage().contains("Maturity is in past"));
//    }
//
//    @Test
//    @Order(9)
//    void post_new_trade_with_past_maturity() {
//        String readUrl = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(5L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().minus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        ResponseEntity<TradeResponse> response = this.restTemplate.postForEntity(readUrl, httpRequest, TradeResponse.class);
//        Assertions.assertNotNull(response);
//        Assertions.assertEquals(422, response.getStatusCodeValue());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(1, response.getBody().getErrorMessage().size());
//        Assertions.assertTrue(response.getBody().getErrorMessage().contains("Maturity is in past"));
//
//    }
//
//    @Test
//    @Order(10)
//    void put_lower_version_and_past_maturity_trade() {
//        String readUrl = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(3L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().minus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        this.restTemplate.put(readUrl, httpRequest, TradeResponse.class);
//    }
//
//    @Test
//    @Order(11)
//    void put_past_maturity_trade() {
//        String readUrl = baseUrl + "/trades/trade";
//        TradeRequest request = new TradeRequest();
//        request.setTradeId("T1");
//        request.setVersion(5L);
//        request.setBookId("B1");
//        request.setCptyId("C1");
//        Date currDate = Date.from(Instant.now());
//        Duration year = Duration.ofDays(365);
//        Date maturityDt = Date.from(Instant.now().minus(year));
//        request.setCreatedDt(currDate);
//        request.setMaturityDt(maturityDt);
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<TradeRequest> httpRequest = new HttpEntity<>(request, headers);
//        this.restTemplate.put(readUrl, httpRequest, ResponseEntity.class);
//    }
//}