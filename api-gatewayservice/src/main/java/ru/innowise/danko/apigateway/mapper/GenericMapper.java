package ru.innowise.danko.apigateway.mapper;

public interface GenericMapper<E, T> {

    T toDto(E entity);

    E toEntity(T dto);
}
