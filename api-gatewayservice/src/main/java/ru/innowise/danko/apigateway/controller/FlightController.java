package ru.innowise.danko.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.apigateway.client.FlightClient;
import ru.innowise.danko.apigateway.dto.FlightDto;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightClient flightClient;

    @Autowired
    public FlightController(FlightClient flightClient) {
        this.flightClient = flightClient;
    }

    @PostMapping
    public Long persist(@RequestBody FlightDto flightDto){
        return flightClient.persist(flightDto);
    }

    @PutMapping("/{id}")
    public FlightDto update(@PathVariable Long id,
                            @RequestBody FlightDto flightDto){
        return flightClient.update(id, flightDto);
    }

    @GetMapping("/{id}")
    public FlightDto findById(@PathVariable Long id){
        return flightClient.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        flightClient.delete(id);
    }
}