package io.github.lucasbuccilli.mediatr;

import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

/**
 * <pre>
 * MediatR interface that is implemented by {@link io.github.lucasbuccilli.mediatr.MediatRImpl}
 *
 * This interface should be used for sending all {@link io.github.lucasbuccilli.mediatr.Message} types.
 * </pre>
 */
public interface MediatR {
    /**
     * Submit a {@link io.github.lucasbuccilli.mediatr.Request}
     * @param request to be submitted
     * @param <TRequest> class implementing {@link io.github.lucasbuccilli.mediatr.Request}
     * @param <TResponse> type of response
     * @return TResponse from the handler
     */
    <TRequest extends Request<TResponse>, TResponse> TResponse send(@NonNull TRequest request);


    /**
     * Submit an asynchronous {@link io.github.lucasbuccilli.mediatr.Request}
     * @param request to be submitted
     * @param <TRequest> class implementing {@link io.github.lucasbuccilli.mediatr.Request}
     * @param <TResponse> type of response
     * @return CompletableFuture containing the response from the handler
     */
    <TRequest extends Request<TResponse>, TResponse> CompletableFuture<TResponse> sendAsync(@NonNull TRequest request);

    /**
     * Submit a {@link io.github.lucasbuccilli.mediatr.Event}
     * @param event to be submitted
     * @param <TRequest> class implementing {@link io.github.lucasbuccilli.mediatr.Event}
     */
    <TRequest extends Event> void send(@NonNull TRequest event);

    /**
     * Submit an asynchronous {@link io.github.lucasbuccilli.mediatr.Event}
     * @param event to be submitted
     * @param <TRequest> class implementing {@link io.github.lucasbuccilli.mediatr.Event}
     * @return Completable future containing {@link java.lang.Void}
     */
    <TRequest extends Event> CompletableFuture<Void> sendAsync(@NonNull TRequest event);
}
