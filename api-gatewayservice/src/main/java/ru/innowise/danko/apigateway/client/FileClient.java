package ru.innowise.danko.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.innowise.danko.apigateway.util.interceptor.FeignRequestInterceptorConfig;

@FeignClient(value = "fileService",
        url = "${ext-service.fileService.baseUrl}",
        configuration = FeignRequestInterceptorConfig.class)
public interface FileClient {

    @RequestMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE ,method = RequestMethod.POST)
    ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file);

    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
    ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String id);
}
