package ru.innowise.danko.notification.listner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.innowise.danko.notification.dto.EventDto;
import ru.innowise.danko.notification.service.EventService;

@Component
public class EventListener {

    private final EventService eventService;
    private final ObjectMapper objectMapper;

    @Autowired
    public EventListener(EventService eventService, ObjectMapper objectMapper) {
        this.eventService = eventService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = KafkaTopics.EVENT_REQUEST_TOPIC)
    public void eventRequestListener(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        eventService.persist(objectMapper.readValue(consumerRecord.value(), EventDto.class));
    }

    @KafkaListener(topics = KafkaTopics.EVENT_RESPONSE_TOPIC)
    public void eventResponseListener(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        EventDto eventResp= objectMapper.readValue(consumerRecord.value(), EventDto.class);
        String traceId = eventResp.getTraceId();
        EventDto eventReq = eventService.getByTraceId(traceId);
        eventReq.setUrlResp(eventResp.getUrlResp());
        eventReq.setBodyResp(eventResp.getBodyResp());
        eventService.persist(eventReq);
    }
}
