package ru.innowise.danko.apigateway.util.interceptor;

import feign.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.innowise.danko.apigateway.util.KafkaTemplateProvider;

@Configuration
public class FeignResponseInterceptorConfig {

    private final KafkaTemplateProvider kafkaTemplateProvider;

    @Autowired
    public FeignResponseInterceptorConfig(KafkaTemplateProvider kafkaTemplateProvider){
        this.kafkaTemplateProvider = kafkaTemplateProvider;
    }

    @Bean
    public Client client(){
        return new FeignResponseInterceptor(null,
                null,
                kafkaTemplateProvider);
    }
}
