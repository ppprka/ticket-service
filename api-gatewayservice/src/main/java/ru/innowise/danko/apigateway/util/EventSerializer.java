package ru.innowise.danko.apigateway.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;
import ru.innowise.danko.apigateway.dto.EventDto;

import java.util.Map;

public class EventSerializer implements Serializer<EventDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EventSerializer() {}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @SneakyThrows
    @Override
    public byte[] serialize(String topic, EventDto data) {
        return objectMapper.writeValueAsBytes(data);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, EventDto data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
