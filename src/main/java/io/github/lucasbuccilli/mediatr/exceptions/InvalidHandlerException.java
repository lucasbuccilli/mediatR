package io.github.lucasbuccilli.mediatr.exceptions;

import io.github.lucasbuccilli.mediatr.Handler;

/**
 * Thrown if a handler is invalid.
 */
public class InvalidHandlerException extends RuntimeException {
    /**
     * @param handler - class that implements {@link Handler}
     * @see io.github.lucasbuccilli.mediatr.RequestHandler
     * @see io.github.lucasbuccilli.mediatr.EventHandler
     */
    public InvalidHandlerException(Class<? extends Handler> handler) {
        super("An invalid handler was found: " + handler.getName());
    }
}
