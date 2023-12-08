package io.github.lucasbuccilli.mediatr.exceptions;

public class DuplicateHandlerException extends RuntimeException {
    public DuplicateHandlerException(Class<?> request) {
        super("A handler is already registered for request type: " + request.getName());
    }
}
