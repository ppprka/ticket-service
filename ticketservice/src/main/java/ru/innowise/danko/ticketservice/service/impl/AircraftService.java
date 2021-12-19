package ru.innowise.danko.ticketservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.innowise.danko.ticketservice.dto.AircraftDto;
import ru.innowise.danko.ticketservice.entity.Aircraft;
import ru.innowise.danko.ticketservice.mapper.GenericMapper;
import ru.innowise.danko.ticketservice.repository.AircraftRepository;

@Service
public class AircraftService extends BaseJpaServiceImpl<Long, Aircraft, AircraftDto>{

    private final AircraftRepository repository;

    @Autowired
    public AircraftService(AircraftRepository repository, GenericMapper<Aircraft, AircraftDto> mapper) {
        super(repository, mapper);
        this.repository = repository;
    }
}
