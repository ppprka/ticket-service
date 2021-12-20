package ru.innowise.danko.apigateway.util.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innowise.danko.apigateway.util.KafkaTemplateProvider;

@Component
public class FeignRequestInterceptorConfig implements RequestInterceptor{

    private final KafkaTemplateProvider kafkaTemplateProvider;

    @Autowired
    public FeignRequestInterceptorConfig(KafkaTemplateProvider kafkaTemplateProvider) {
        this.kafkaTemplateProvider = kafkaTemplateProvider;
    }

    @Override
    public void apply(RequestTemplate template) {
        if(!template.path().contains("/file")) {
            try {
                kafkaTemplateProvider
                        .sendMessage(template.path() + ", " + template.method(),
                                new ObjectMapper().writeValueAsString(template.body()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
