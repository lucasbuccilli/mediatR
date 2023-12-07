package com.lucasbuccilli.mediatr.exceptions;

import com.lucasbuccilli.mediatr.Request;

public class DuplicateHandlerException extends RuntimeException {
    public DuplicateHandlerException(Class<?> request) {
        super("A handler is already registered for request type: " + request.getName());
    }
}
