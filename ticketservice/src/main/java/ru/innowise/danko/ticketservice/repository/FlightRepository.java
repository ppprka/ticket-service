package ru.innowise.danko.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innowise.danko.ticketservice.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
