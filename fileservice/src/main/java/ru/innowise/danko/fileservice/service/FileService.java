package ru.innowise.danko.fileservice.service;

import org.springframework.web.multipart.MultipartFile;
import ru.innowise.danko.fileservice.dto.FileDto;

public interface FileService {

    String uploadFile(MultipartFile multipartFile);

    FileDto downloadFile(String id);

}