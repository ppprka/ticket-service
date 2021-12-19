package ru.innowise.danko.ticketservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "aircraft", schema = "ticket_service")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producer;

    private String model;

    @OneToOne(mappedBy = "aircraft")
    private Flight flight;

    @Override
    public String toString() {
        return new StringJoiner(", ", Aircraft.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("producer='" + producer + "'")
                .add("model='" + model + "'")
                .toString();
    }
}
