package ru.innowise.danko.apigateway.util.interceptor;

import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.util.StreamUtils;
import ru.innowise.danko.apigateway.util.KafkaTemplateProvider;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class FeignResponseInterceptor extends Client.Default{

    private final KafkaTemplateProvider kafkaTemplateProvider;

    public FeignResponseInterceptor(SSLSocketFactory sslContextFactory,
                                    HostnameVerifier hostnameVerifier,
                                    KafkaTemplateProvider kafkaTemplateProvider) {
        super(sslContextFactory, hostnameVerifier);
        this.kafkaTemplateProvider = kafkaTemplateProvider;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        Response response = super.execute(request, options);
        InputStream bodyStream = response.body().asInputStream();
        String responseBody = StreamUtils.copyToString(bodyStream, StandardCharsets.UTF_8);
        kafkaTemplateProvider.sendMessage(Integer.toString(response.status()), responseBody);
        return response.toBuilder().body(responseBody, StandardCharsets.UTF_8).build();
    }
}
