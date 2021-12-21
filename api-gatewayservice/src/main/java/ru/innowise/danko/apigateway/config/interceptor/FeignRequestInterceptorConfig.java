package ru.innowise.danko.apigateway.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innowise.danko.apigateway.config.KafkaSender;
import ru.innowise.danko.apigateway.config.UrlValidator;
import ru.innowise.danko.apigateway.dto.EventDto;

import java.util.Arrays;

import static ru.innowise.danko.apigateway.config.KafkaTopics.EVENT_TOPIC;

@Component
public class FeignRequestInterceptorConfig implements RequestInterceptor{

    private final KafkaSender kafkaSender;
    private final ObjectMapper objectMapper;
    private final UrlValidator urlValidator;

    @Autowired
    public FeignRequestInterceptorConfig(KafkaSender kafkaSender,
                                         ObjectMapper objectMapper,
                                         UrlValidator urlValidator) {
        this.kafkaSender = kafkaSender;
        this.objectMapper = objectMapper;
        this.urlValidator = urlValidator;
    }

    @Override
    public void apply(RequestTemplate template) {
        if(Arrays.stream(urlValidator.getEventAvoidUrlList()).noneMatch(template.path()::equals)) {
            kafkaSender.send(EVENT_TOPIC, objectToJson(EventDto
                    .builder()
                    .url(template.url())
                    .body(template.requestBody().asString())
                    .build()));
        }
    }

    @SneakyThrows
    public String objectToJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
