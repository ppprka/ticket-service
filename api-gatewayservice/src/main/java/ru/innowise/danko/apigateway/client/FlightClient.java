package ru.innowise.danko.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.apigateway.dto.FlightDto;
import ru.innowise.danko.apigateway.config.interceptor.FeignRequestInterceptorConfig;

@FeignClient(name = "ticketService",
        url = "${ext-service.ticketService.baseUrl}",
        configuration = FeignRequestInterceptorConfig.class)
public interface FlightClient {

    @PostMapping("/api/flight")
    FlightDto persist(@RequestBody FlightDto flightDto);

    @PutMapping("/api/flight/{id}")
    FlightDto update(@PathVariable Long id,
                            @RequestBody FlightDto flightDto);

    @GetMapping("/api/flight/{id}")
    FlightDto findById(@PathVariable Long id);

    @DeleteMapping("/api/flight/{id}")
    void delete(@PathVariable Long id);
}
