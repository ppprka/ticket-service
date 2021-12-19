package ru.innowise.danko.ticketservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import ru.innowise.danko.ticketservice.dto.FlightDto;
import ru.innowise.danko.ticketservice.entity.Flight;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {TicketMapper.class, AircraftMapper.class})
public interface FlightMapper extends GenericMapper<Flight, FlightDto> {

    @Override
    @Mapping(target = "aircraftId", ignore = true)
    FlightDto toDto(Flight entity);

    @Override
    @Mapping(target = "aircraft", ignore = true)
    Flight toEntity(FlightDto dto);
}
