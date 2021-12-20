package ru.innowise.danko.apigateway.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import ru.innowise.danko.apigateway.dto.EventDto;

@Configuration
public class KafkaTemplateProvider {

    private final KafkaTemplate<String, EventDto> kafkaTemplate;


    @Autowired
    public KafkaTemplateProvider(KafkaTemplate<String, EventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String urlMethod, String body){
        ListenableFuture<SendResult<String, EventDto>> future = kafkaTemplate
                .send("event", EventDto.builder()
                        .body(body)
                        .urlMethod(urlMethod)
                        .build());
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
