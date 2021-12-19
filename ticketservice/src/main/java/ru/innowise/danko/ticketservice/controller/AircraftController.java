package ru.innowise.danko.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.ticketservice.dto.AircraftDto;
import ru.innowise.danko.ticketservice.dto.FlightDto;
import ru.innowise.danko.ticketservice.service.impl.AircraftService;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }


    @PostMapping()
    public Long persist(@RequestBody AircraftDto aircraftDto){
        return aircraftService.persist(aircraftDto).getId();
    }

    @PutMapping("/{id}")
    public AircraftDto update(@PathVariable Long id,
                            @RequestBody AircraftDto aircraftDto){
        return aircraftService.persist(aircraftDto);
    }

    @GetMapping("/{id}")
    public AircraftDto findById(@PathVariable Long id){
        return aircraftService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        aircraftService.removeById(id);
    }
}
