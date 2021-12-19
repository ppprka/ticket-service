package ru.innowise.danko.fileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innowise.danko.fileservice.dto.FileDto;
import ru.innowise.danko.fileservice.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {

    public FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok().body(fileService.uploadFile(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable String id) {
        FileDto file = fileService.downloadFile(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +file.getName())
                .body(new ByteArrayResource(file.getFile()));
    }

}
