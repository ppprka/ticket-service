package ru.innowise.danko.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innowise.danko.apigateway.client.FileClient;

@RestController
@RequestMapping("/files")
public class FileController {

    public FileClient fileClient;

    @Autowired
    public FileController(FileClient fileClient) {
        this.fileClient = fileClient;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        return fileClient.uploadFile(file);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable String id) {
        return fileClient.downloadFile(id);
    }

}
