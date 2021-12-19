package ru.innowise.danko.apigateway.dto;

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

}
