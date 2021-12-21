package ru.innowise.danko.apigateway.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UrlValidator {

    private final String[] eventAvoidUrlList = {
            "/file"
    };
}
