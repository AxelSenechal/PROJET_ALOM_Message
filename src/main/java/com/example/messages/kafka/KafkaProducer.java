package com.example.messages.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.messages.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaProducer {
    private static final String TOPIC = "conversations";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Message message) throws JsonProcessingException {
        String messageJson = objectMapper.writeValueAsString(message);
        kafkaTemplate.send(TOPIC, message.getReceiverId(), messageJson);
    }
}
