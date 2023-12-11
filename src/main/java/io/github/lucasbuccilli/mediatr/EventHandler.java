package io.github.lucasbuccilli.mediatr;

import lombok.NonNull;

/**
 * Interface used to define a handler for an event.
 * @param <TRequest> Class of event that the handler will handler
 */
public interface EventHandler<TRequest extends Event> {
    /**
     * Handles the {@link io.github.lucasbuccilli.mediatr.Event}
     * @param event to be handled
     */
    void handle(@NonNull TRequest event);
}
