package com.lucasbuccilli.mediatr.exceptions;

import com.lucasbuccilli.mediatr.Request;

public class MissingHandlerException extends RuntimeException {
    public MissingHandlerException(Class<Request<?>> request) {
        super("A handler is not registered for the request type: " + request.getName());
    }
}
