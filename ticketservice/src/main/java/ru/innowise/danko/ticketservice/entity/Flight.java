package ru.innowise.danko.ticketservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flight", schema = "ticket_service")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "arrival_airport")
    private String arrivalAirport;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flight")
    private Set<Ticket> ticketSet;

    @Override
    public String toString() {
        return new StringJoiner(", ", Flight.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("flightNumber='" + flightNumber + "'")
                .add("departureAirport='" + departureAirport + "'")
                .add("arrivalAirport='" + arrivalAirport + "'")
                .add("departureTime=" + departureTime)
                .add("arrivalTime=" + arrivalTime)
                .add("ticketSet=" + ticketSet)
                .toString();
    }
}
