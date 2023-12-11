package io.github.lucasbuccilli.mediatr.exceptions;

import io.github.lucasbuccilli.mediatr.Message;

/**
 * Thrown if no handlers are registered for the message.
 */
public class MissingHandlerException extends RuntimeException {
    /**
     * @param message class that is missing a handler
     */
    public MissingHandlerException(Class<? extends Message> message) {
        super("A handler is not registered for the message type: " + message.getName());
    }
}
