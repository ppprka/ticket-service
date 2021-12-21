package ru.innowise.danko.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innowise.danko.notification.dto.EventDto;
import ru.innowise.danko.notification.repository.EventRepository;
import ru.innowise.danko.notification.util.ObjectNotFound;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventDto persist(EventDto eventDto){
        return eventRepository.save(eventDto);
    }

    public EventDto getById(String id){
        return eventRepository.findById(id).orElseThrow(() ->
                new ObjectNotFound(EventDto.class, id));
    }

    public EventDto getByTraceId(String id){
        return eventRepository.findEventDtoByTraceId(id);
    }

    public void delete(String id){
        eventRepository
                .delete(eventRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ObjectNotFound(EventDto.class, id)));
    }
}
