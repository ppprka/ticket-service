package ru.innowise.danko.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.apigateway.client.AircraftClient;
import ru.innowise.danko.apigateway.dto.AircraftDto;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftClient aircraftClient;

    @Autowired
    public AircraftController(AircraftClient aircraftClient) {
        this.aircraftClient = aircraftClient;
    }


    @PostMapping()
    public Long persist(@RequestBody AircraftDto aircraftDto){
        return aircraftClient.persist(aircraftDto);
    }

    @PutMapping("/{id}")
    public AircraftDto update(@PathVariable Long id,
                              @RequestBody AircraftDto aircraftDto){
        return aircraftClient.update(id, aircraftDto);
    }

    @GetMapping("/{id}")
    public AircraftDto findById(@PathVariable Long id){
        return aircraftClient.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        aircraftClient.delete(id);
    }
}