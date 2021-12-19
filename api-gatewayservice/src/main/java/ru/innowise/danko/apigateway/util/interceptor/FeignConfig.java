package ru.innowise.danko.apigateway.util.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class FeignConfig implements RequestInterceptor {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public FeignConfig(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void apply(RequestTemplate template) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
                .send("event", template.path()+", "+template.method());
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
