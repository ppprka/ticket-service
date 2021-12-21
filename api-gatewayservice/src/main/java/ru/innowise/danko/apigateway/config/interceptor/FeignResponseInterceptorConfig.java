package ru.innowise.danko.apigateway.config.interceptor;

import brave.Tracer;
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
    private final Tracer tracer;

    @Autowired
    public FeignResponseInterceptorConfig(KafkaSender kafkaSender, UrlValidator urlValidator, ObjectMapper objectMapper, Tracer tracer){
        this.kafkaSender = kafkaSender;
        this.urlValidator = urlValidator;
        this.objectMapper = objectMapper;
        this.tracer = tracer;
    }

    @Bean
    public Client client(){
        return new FeignResponseInterceptor(null,
                null,
                kafkaSender, urlValidator, objectMapper, tracer);
    }
}
