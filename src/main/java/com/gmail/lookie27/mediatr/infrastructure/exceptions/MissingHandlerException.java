package com.gmail.lookie27.mediatr.infrastructure.exceptions;

import com.gmail.lookie27.mediatr.infrastructure.Request;

public class MissingHandlerException extends RuntimeException {
    public MissingHandlerException(Class<Request<?>> request) {
        super("A handler is not registered for the request type: " + request.getName());
    }
}
