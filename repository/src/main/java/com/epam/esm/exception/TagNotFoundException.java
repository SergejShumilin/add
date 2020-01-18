package com.epam.esm.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(int id){
        super(String.valueOf(id));
    }

    public TagNotFoundException(String message) {
        super(message);
    }
}
