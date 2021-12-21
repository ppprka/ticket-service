package ru.innowise.danko.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.ticketservice.dto.FlightDto;
import ru.innowise.danko.ticketservice.service.impl.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public FlightDto persist(@RequestBody FlightDto flightDto){
        return flightService.persist(flightDto);
    }

    @PutMapping("/{id}")
    public FlightDto update(@PathVariable Long id,
                            @RequestBody FlightDto flightDto){
        return flightService.persist(flightDto);
    }

    @GetMapping("/{id}")
    public FlightDto findById(@PathVariable Long id){
        return flightService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        flightService.removeById(id);
    }
}
