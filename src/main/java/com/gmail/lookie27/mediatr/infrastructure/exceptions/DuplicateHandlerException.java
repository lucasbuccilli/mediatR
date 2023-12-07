package com.gmail.lookie27.mediatr.infrastructure.exceptions;

import com.gmail.lookie27.mediatr.infrastructure.Request;

public class DuplicateHandlerException extends RuntimeException {
    public DuplicateHandlerException(Class<Request<?>> request) {
        super("A handler is already registered for request type: " + request.getName());
    }
}
