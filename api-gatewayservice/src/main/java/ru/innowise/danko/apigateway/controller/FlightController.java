package ru.innowise.danko.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.apigateway.client.FlightClient;
import ru.innowise.danko.apigateway.dto.FlightDto;

import java.security.Principal;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightClient flightClient;

    @Autowired
    public FlightController(FlightClient flightClient) {
        this.flightClient = flightClient;
    }

    @PostMapping
    public FlightDto persist(@RequestBody FlightDto flightDto){
        return flightClient.persist(flightDto);
    }

    @PutMapping("/{id}")
    public FlightDto update(@PathVariable Long id,
                            @RequestBody FlightDto flightDto){
        return flightClient.update(id, flightDto);
    }

    @PutMapping("/aircraft-id/{id}")
    public ResponseEntity<FlightDto> updateAircraftId(@PathVariable Long id,
                                                      @RequestBody FlightDto flightDto,
                                                      @Autowired Principal principal){
        flightDto.setAircraftId(id);
        return ResponseEntity.ok(flightClient.persist(flightDto));
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