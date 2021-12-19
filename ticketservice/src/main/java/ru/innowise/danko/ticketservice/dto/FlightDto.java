package ru.innowise.danko.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDto {
    private Long id;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long aircraftId;
    private Set<TicketDto> ticketSet;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlightDto{");
        sb.append("id=").append(id);
        sb.append(", flightNumber='").append(flightNumber).append('\'');
        sb.append(", departureAirport='").append(departureAirport).append('\'');
        sb.append(", arrivalAirport='").append(arrivalAirport).append('\'');
        sb.append(", departureTime=").append(departureTime);
        sb.append(", arrivalTime=").append(arrivalTime);
        sb.append(", ticketSet=").append(ticketSet);
        sb.append('}');
        return sb.toString();
    }
}
