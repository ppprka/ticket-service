package ru.innowise.danko.ticketservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.innowise.danko.ticketservice.mapper.GenericMapper;
import ru.innowise.danko.ticketservice.service.BaseJpaService;

public class BaseJpaServiceImpl<ID, ENTITY, DTO> implements BaseJpaService<ID, DTO> {

    private final JpaRepository<ENTITY, ID> repository;
    private final GenericMapper<ENTITY,DTO> mapper;

    public BaseJpaServiceImpl(JpaRepository<ENTITY, ID> repository, GenericMapper<ENTITY, DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DTO persist(DTO dto) {
        ENTITY entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public DTO findById(ID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow();
    }

    @Override
    public void removeById(ID id) {
        repository.findById(id).orElseThrow();
        repository.deleteById(id);
    }
}
