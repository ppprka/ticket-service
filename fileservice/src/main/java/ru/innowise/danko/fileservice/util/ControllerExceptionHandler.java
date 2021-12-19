package ru.innowise.danko.fileservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.innowise.danko.fileservice.dto.ExceptionDto;
import ru.innowise.danko.fileservice.util.exception.FileNotFound;

import java.time.LocalDateTime;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileNotFound.class)
    public ResponseEntity<ExceptionDto> fileNotFoundHandleException(FileNotFound e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

}
