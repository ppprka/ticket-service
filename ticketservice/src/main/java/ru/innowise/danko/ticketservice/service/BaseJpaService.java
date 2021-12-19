package ru.innowise.danko.ticketservice.service;

public interface BaseJpaService<ID, DTO> {

    DTO persist(DTO dto);

    DTO findById(ID id);

    void removeById(ID id);
}
