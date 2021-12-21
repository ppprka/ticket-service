package ru.innowise.danko.apigateway.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
import feign.Request;
import feign.Response;
import lombok.SneakyThrows;
import org.springframework.util.StreamUtils;
import ru.innowise.danko.apigateway.config.KafkaSender;
import ru.innowise.danko.apigateway.config.UrlValidator;
import ru.innowise.danko.apigateway.dto.EventDto;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static ru.innowise.danko.apigateway.config.KafkaTopics.EVENT_TOPIC;


public class FeignResponseInterceptor extends Client.Default{

    private final KafkaSender kafkaSender;
    private final UrlValidator urlValidator;
    private final ObjectMapper objectMapper;

    public FeignResponseInterceptor(SSLSocketFactory sslContextFactory,
                                    HostnameVerifier hostnameVerifier,
                                    KafkaSender kafkaSender, UrlValidator urlValidator, ObjectMapper objectMapper) {
        super(sslContextFactory, hostnameVerifier);
        this.kafkaSender = kafkaSender;
        this.urlValidator = urlValidator;
        this.objectMapper = objectMapper;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        Response response = super.execute(request, options);
        InputStream bodyStream = response.body().asInputStream();
        String responseBody = StreamUtils.copyToString(bodyStream, StandardCharsets.UTF_8);
        if(Arrays.stream(urlValidator.getEventAvoidUrlList()).noneMatch(request.url()::equals)) {
            kafkaSender.send(EVENT_TOPIC,
                    objectToJson(EventDto
                    .builder()
                            .url(request.url())
                    .body(responseBody)
                    .build()));
        }
        return response.toBuilder().body(responseBody, StandardCharsets.UTF_8).build();
    }

    @SneakyThrows
    public String objectToJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
