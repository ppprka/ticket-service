package ru.innowise.danko.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {

    private String id;
    private String traceId;
    private String urlReq;
    private String urlResp;
    private String bodyReq;
    private String bodyResp;
}
