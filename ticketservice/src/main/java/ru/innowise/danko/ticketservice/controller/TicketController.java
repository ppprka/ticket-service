package ru.innowise.danko.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innowise.danko.ticketservice.dto.TicketDto;
import ru.innowise.danko.ticketservice.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    public ResponseEntity<TicketDto> create(@RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.persist(ticketDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> update(@PathVariable Long id,
                                            @RequestBody TicketDto ticketDto){
        return ResponseEntity.ok(ticketService.persist(ticketDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        ticketService.delete(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getBuyId(id));
    }

    @GetMapping
    public ResponseEntity<Page<TicketDto>> getAllAvailable(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy){
        return ResponseEntity.ok(ticketService.getAllAvailable(pageNumber,pageSize,sortBy));
    }

    @GetMapping("/booked")
    public ResponseEntity<Page<TicketDto>> getBooked(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam Long userId){
        return ResponseEntity.ok(ticketService.getUserTickets(userId,pageNumber,pageSize,sortBy));
    }

    @PutMapping("/book/{id}")
    public TicketDto book(@PathVariable Long id){
        return ticketService.book(id);
    }
}
