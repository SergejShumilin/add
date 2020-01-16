package com.epam.esm.dao.exception;

public class TagExistsException extends RuntimeException{
    public TagExistsException(String message) {
        super(message);
    }
}
