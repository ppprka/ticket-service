package ru.innowise.danko.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.apigateway.dto.TicketDto;
import ru.innowise.danko.apigateway.util.interceptor.FeignRequestInterceptorConfig;

@FeignClient(name = "ticketService",
        url = "${ext-service.ticketService.baseUrl}",
        configuration = FeignRequestInterceptorConfig.class)
public interface TicketClient {

    @PostMapping("/api/ticket")
    TicketDto persist(@RequestBody TicketDto ticketDto);

    @GetMapping("/api/ticket/{id}")
    TicketDto getById(@PathVariable Long id);

    @GetMapping
    Page<TicketDto> getAllAvailable(@RequestParam(defaultValue = "0") Integer pageNumber,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "id") String sortBy);

    @GetMapping("/booked")
    Page<TicketDto> getBooked(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam Long userId);
    @PutMapping("/api/ticket/{id}")
    TicketDto update(@RequestBody TicketDto ticketDto);

    @DeleteMapping("/api/ticket/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/api/ticket/book/{id}")
    TicketDto book(@PathVariable Long id);

}
