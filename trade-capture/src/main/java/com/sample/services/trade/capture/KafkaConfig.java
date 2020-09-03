package com.sample.services.trade.capture;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value(value = "${kafka.server}")
    private String server;
    @Value(value = "${kafka.port}")
    private Integer port;
    @Value(value = "${kafka.topic}")
    private String topic;

    @Bean
    public EmbeddedKafkaBroker kafkaBroker() {
        EmbeddedKafkaBroker embeddedKafka = new EmbeddedKafkaBroker(1, true, 4, topic);
        embeddedKafka.kafkaPorts(port);
        Map<String, String> properties = new HashMap<>();
        properties.put("log.dirs", "kafka-log");
        embeddedKafka =  embeddedKafka.brokerProperties(properties);
        return embeddedKafka;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic tradeTopic() {
        return new NewTopic(topic, 1, (short) 1);
    }
}
