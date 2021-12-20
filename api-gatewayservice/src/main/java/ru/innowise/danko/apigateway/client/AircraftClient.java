package ru.innowise.danko.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.apigateway.dto.AircraftDto;
import ru.innowise.danko.apigateway.util.interceptor.FeignRequestInterceptorConfig;
import ru.innowise.danko.apigateway.util.interceptor.FeignResponseInterceptorConfig;

@FeignClient(name = "ticketService",
        url = "${ext-service.ticketService.baseUrl}",
        configuration = {FeignRequestInterceptorConfig.class,
                FeignResponseInterceptorConfig.class})
public interface AircraftClient {

    @PostMapping("/api/aircraft")
    public Long persist(@RequestBody AircraftDto aircraftDto);

    @PutMapping("/api/aircraft/{id}")
    public AircraftDto update(@PathVariable Long id,
                              @RequestBody AircraftDto aircraftDto);

    @GetMapping("/api/aircraft/{id}")
    public AircraftDto findById(@PathVariable Long id);

    @DeleteMapping("/api/aircraft/{id}")
    public void delete(@PathVariable Long id);
}
