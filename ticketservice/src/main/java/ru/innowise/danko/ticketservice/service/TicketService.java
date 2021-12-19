package ru.innowise.danko.ticketservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innowise.danko.ticketservice.dto.TicketDto;
import ru.innowise.danko.ticketservice.entity.Ticket;
import ru.innowise.danko.ticketservice.mapper.TicketMapper;
import ru.innowise.danko.ticketservice.repository.TicketRepository;
import ru.innowise.danko.ticketservice.repository.specification.SearchCriteria;
import ru.innowise.danko.ticketservice.repository.specification.TicketSpecification;
import ru.innowise.danko.ticketservice.util.ObjectNotFound;

import java.util.Optional;

@Service
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    public TicketDto persist(TicketDto ticketDto) {
        return ticketMapper.toDto(ticketRepository
                .save(ticketMapper.toEntity(ticketDto)));
    }

    public TicketDto getBuyId(Long id) {
        return Optional.of(ticketMapper.toDto(ticketRepository.getById(id)))
                .orElseThrow(() -> new ObjectNotFound(Ticket.class, id));
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    public Page<TicketDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy) {
        TicketSpecification ticketSpecification =
                new TicketSpecification(new SearchCriteria("available",":",true));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Ticket> pageResult = ticketRepository.findAll(ticketSpecification,pageable);
        return pageResult.map(ticketMapper::toDto);
    }

    public Page<TicketDto> getUserTickets(Long userId, Integer pageNumber, Integer pageSize, String sortBy) {
        TicketSpecification ticketSpecification =
                new TicketSpecification(new SearchCriteria("userId",":",userId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Ticket> pageResult = ticketRepository.findAll(ticketSpecification,pageable);
        return pageResult.map(ticketMapper::toDto);
    }

    public TicketDto book(Long ticketId){
        Ticket ticket = Optional.of(ticketRepository.getById(ticketId))
                .orElseThrow(() -> new ObjectNotFound(Ticket.class, ticketId));
        ticket.setAvailable(false);
        return ticketMapper.toDto(ticketRepository.save(ticket));
    }
}
