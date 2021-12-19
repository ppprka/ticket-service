package ru.innowise.danko.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftDto {
    private Long id;
    private String producer;
    private String model;
    private Long flightId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AircraftDto{");
        sb.append("id=").append(id);
        sb.append(", producer='").append(producer).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
