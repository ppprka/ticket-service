package ru.innowise.danko.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
    private Long id;
    private String ticketNumber;
    private String gate;
    private String seat;
    private String classType;
    private Long userId;
    private Long flightId;
    private boolean available = true;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TicketDto{");
        sb.append("id=").append(id);
        sb.append(", ticketNumber='").append(ticketNumber).append('\'');
        sb.append(", gate='").append(gate).append('\'');
        sb.append(", seat='").append(seat).append('\'');
        sb.append(", classType='").append(classType).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", flight=").append(flightId);
        sb.append('}');
        return sb.toString();
    }
}
