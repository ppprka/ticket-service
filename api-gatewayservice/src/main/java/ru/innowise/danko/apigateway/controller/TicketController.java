package ru.innowise.danko.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.apigateway.client.AircraftClient;
import ru.innowise.danko.apigateway.client.FlightClient;
import ru.innowise.danko.apigateway.client.TicketClient;
import ru.innowise.danko.apigateway.dto.TicketDto;
import ru.innowise.danko.apigateway.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketClient ticketClient;
    private final UserService userService;
    private final FlightClient flightClient;
    private final AircraftClient aircraftClient;

    @Autowired
    public TicketController(TicketClient ticketClient,
                            UserService userService,
                            FlightClient flightClient,
                            AircraftClient aircraftClient) {
        this.ticketClient = ticketClient;
        this.userService = userService;
        this.flightClient = flightClient;
        this.aircraftClient = aircraftClient;
    }


    @PostMapping
    public ResponseEntity<TicketDto> create(@RequestBody TicketDto ticketDto,
                                            @Autowired Principal principal){
        return ResponseEntity.ok(ticketClient.persist(ticketDto));
    }

    @PutMapping("/flight-id/{id}")
    public ResponseEntity<TicketDto> updateFlightId(@PathVariable Long id,
                                            @RequestBody TicketDto ticketDto,
                                            @Autowired Principal principal){
        ticketDto.setFlightId(id);
        return ResponseEntity.ok(ticketClient.persist(ticketDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @Autowired Principal principal){
        ticketClient.delete(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable Long id,
                                             @Autowired Principal principal){
        return ResponseEntity.ok(ticketClient.getById(id));
    }

    @GetMapping
    public Page<TicketDto> getAllAvailable(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy,
                                           @Autowired Principal principal){
        return ticketClient.getAllAvailable(pageNumber,pageSize,sortBy);
    }

    @GetMapping("/booked")
    public Page<TicketDto> getBooked(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @Autowired Principal principal){
        return ticketClient
                .getBooked(pageNumber,pageSize,sortBy,userService
                        .getUserIdByUsername(principal.getName()));
    }

    @PutMapping("/api/ticket/book/{id}")
    public TicketDto book(@PathVariable Long id,@Autowired Principal principal){
        TicketDto ticketDto = ticketClient.book(id);
        ticketDto.setUserId(userService.getUserIdByUsername(principal.getName()));
        return ticketClient.persist(ticketDto);
    }


}
