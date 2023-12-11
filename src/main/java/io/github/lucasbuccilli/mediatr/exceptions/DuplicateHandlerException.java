package io.github.lucasbuccilli.mediatr.exceptions;

import io.github.lucasbuccilli.mediatr.Message;

/**
 * Thrown if multiple handlers are registered for a message.
 * This only applies for one to one message handler relationships.
 */
public class DuplicateHandlerException extends RuntimeException {
    /**
     * @param message - class that implements {@link io.github.lucasbuccilli.mediatr.Message}
     * @see io.github.lucasbuccilli.mediatr.RequestHandler
     * @see io.github.lucasbuccilli.mediatr.EventHandler
     */
    public DuplicateHandlerException(Class<? extends Message> message) {
        super("A handler is already registered for message type: " + message.getName());
    }
}
