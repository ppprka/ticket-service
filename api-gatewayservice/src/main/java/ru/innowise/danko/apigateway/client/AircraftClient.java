package ru.innowise.danko.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.apigateway.dto.AircraftDto;
import ru.innowise.danko.apigateway.config.interceptor.FeignRequestInterceptorConfig;
import ru.innowise.danko.apigateway.config.interceptor.FeignResponseInterceptorConfig;

@FeignClient(name = "ticketService",
        url = "${ext-service.ticketService.baseUrl}",
        configuration = {FeignRequestInterceptorConfig.class,
                FeignResponseInterceptorConfig.class})
public interface AircraftClient {

    @PostMapping("/api/aircraft")
    AircraftDto persist(@RequestBody AircraftDto aircraftDto);

    @PutMapping("/api/aircraft/{id}")
    AircraftDto update(@PathVariable Long id,
                              @RequestBody AircraftDto aircraftDto);

    @GetMapping("/api/aircraft/{id}")
    AircraftDto findById(@PathVariable Long id);

    @DeleteMapping("/api/aircraft/{id}")
    void delete(@PathVariable Long id);
}
