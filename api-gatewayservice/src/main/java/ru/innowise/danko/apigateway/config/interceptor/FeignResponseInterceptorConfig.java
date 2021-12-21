package ru.innowise.danko.apigateway.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.innowise.danko.apigateway.config.KafkaSender;
import ru.innowise.danko.apigateway.config.UrlValidator;

@Configuration
public class FeignResponseInterceptorConfig {

    private final KafkaSender kafkaSender;
    private final UrlValidator urlValidator;
    private final ObjectMapper objectMapper;

    @Autowired
    public FeignResponseInterceptorConfig(KafkaSender kafkaSender, UrlValidator urlValidator, ObjectMapper objectMapper){
        this.kafkaSender = kafkaSender;
        this.urlValidator = urlValidator;
        this.objectMapper = objectMapper;
    }

    @Bean
    public Client client(){
        return new FeignResponseInterceptor(null,
                null,
                kafkaSender, urlValidator, objectMapper);
    }
}
