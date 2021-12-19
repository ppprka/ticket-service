package ru.innowise.danko.ticketservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.StringJoiner;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tickets", schema = "ticket_service")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number")
    private String ticketNumber;

    private String gate;

    private String seat;

    @Column(name = "class")
    private String classType;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;

    @Column(name = "available")
    private boolean available = true;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Override
    public String toString() {
        return new StringJoiner(", ", Ticket.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("ticketNumber='" + ticketNumber + "'")
                .add("gate='" + gate + "'")
                .add("seat='" + seat + "'")
                .add("classType='" + classType + "'")
                .add("userId=" + userId)
                .add("flight=" + flight)
                .toString();
    }
}
