package ru.innowise.danko.apigateway.security;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationSettings {

    private final String[] whiteList = {"/",
            "/api/user/registration",
            "/api/user/upload-avatar",
            "/api/user/download-avatar/**",
            "/api/ticket"};
}
