package ru.innowise.danko.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.innowise.danko.notification.dto.EventDto;

@Repository
public interface EventRepository extends MongoRepository<EventDto, String> {
}
