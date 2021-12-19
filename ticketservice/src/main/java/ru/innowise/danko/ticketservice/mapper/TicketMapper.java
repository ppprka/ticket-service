package ru.innowise.danko.ticketservice.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innowise.danko.ticketservice.dto.FlightDto;
import ru.innowise.danko.ticketservice.dto.TicketDto;
import ru.innowise.danko.ticketservice.entity.Ticket;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {FlightMapper.class})
public interface TicketMapper extends GenericMapper<Ticket, TicketDto> {

    @Mapping(target = "flightId", ignore = true)
    @Override
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "flight", ignore = true)
    @Override
    Ticket toEntity(TicketDto ticketDto);

//
//    @AfterMapping
//    default void setUserAndFlightToTicket(@MappingTarget TicketDto ticketDto, Ticket ticket,
//                                          @Autowired FlightMapper flightMapper) {
//        FlightDto flightDto = flightMapper.toDto(ticket.getFlight());
//        ticketDto.setFlight(flightDto);
//    }
}
