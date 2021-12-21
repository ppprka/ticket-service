package ru.innowise.danko.notification.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import ru.innowise.danko.notification.dto.EventDto;

import java.nio.charset.StandardCharsets;
import java.util.Map;


public class EventDeserializer implements Deserializer<EventDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EventDeserializer(){}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @SneakyThrows
    @Override
    public EventDto deserialize(String topic, byte[] data) {
        return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), EventDto.class);
    }

    @Override
    public EventDto deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
