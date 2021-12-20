package ru.innowise.danko.notification.listner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.innowise.danko.notification.dto.EventDto;
import ru.innowise.danko.notification.service.EventService;

@Component
public class EventListener {

    private final EventService eventService;

    @Autowired
    public EventListener(EventService eventService) {
        this.eventService = eventService;
    }

    @KafkaListener(topics = "event")
    public void eventListener(ConsumerRecord<String, EventDto> consumerRecord){
            eventService.persist(consumerRecord.value());
    }
}
