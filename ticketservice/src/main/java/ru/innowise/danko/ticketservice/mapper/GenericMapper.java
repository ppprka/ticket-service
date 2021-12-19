package ru.innowise.danko.ticketservice.mapper;

public interface GenericMapper<E, T> {

    T toDto(E entity);

    E toEntity(T dto);
}
