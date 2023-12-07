package com.lucasbuccilli.mediatr.exceptions;

public class MissingHandlerException extends RuntimeException {
    public MissingHandlerException(Class<?> request) {
        super("A handler is not registered for the request type: " + request.getName());
    }
}
