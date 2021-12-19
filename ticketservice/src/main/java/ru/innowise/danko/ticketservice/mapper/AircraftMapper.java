package ru.innowise.danko.ticketservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import ru.innowise.danko.ticketservice.dto.AircraftDto;
import ru.innowise.danko.ticketservice.entity.Aircraft;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {FlightMapper.class})
public interface AircraftMapper extends GenericMapper<Aircraft, AircraftDto> {

    @Override
    @Mapping(target = "flightId", ignore = true)
    AircraftDto toDto(Aircraft aircraft);

    @Override
    @Mapping(target = "flight", ignore = true)
    Aircraft toEntity(AircraftDto aircraftDto);
}
