package io.github.lucasbuccilli.mediatr;

import lombok.NonNull;

/**
 * Request handlers will be registered by {@link io.github.lucasbuccilli.mediatr.HandlerRegistry}
 * @param <TRequest> class that implements {@link io.github.lucasbuccilli.mediatr.Request}
 * @param <TResponse> response type from the handler when processing a TRequest
 */
public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {
    /**
     * Handles the {@link io.github.lucasbuccilli.mediatr.Request}
     * @param request to be handled
     * @return response from the handler
     */
    TResponse handle(@NonNull TRequest request);
}
