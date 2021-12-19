package ru.innowise.danko.ticketservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innowise.danko.ticketservice.dto.FlightDto;
import ru.innowise.danko.ticketservice.entity.Flight;
import ru.innowise.danko.ticketservice.mapper.GenericMapper;
import ru.innowise.danko.ticketservice.repository.FlightRepository;

@Service
public class FlightService extends BaseJpaServiceImpl<Long, Flight, FlightDto> {

    private final FlightRepository repository;

    @Autowired
    public FlightService(FlightRepository repository, GenericMapper<Flight, FlightDto> mapper) {
        super(repository, mapper);
        this.repository = repository;
    }
}
