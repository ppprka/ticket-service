package ru.innowise.danko.fileservice.util.exception;

public class FileNotFound extends RuntimeException {

    public FileNotFound(String id){
        super("File with id " + id + " doesnt exist");
    }
}