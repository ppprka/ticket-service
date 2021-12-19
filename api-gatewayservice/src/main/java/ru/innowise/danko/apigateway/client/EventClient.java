package ru.innowise.danko.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "eventService",
        url = "${ext-service.eventService.baseUrl}")
public interface EventClient {
}
