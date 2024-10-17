package io.github.lucasbuccilli.mediatr;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CommonsLog
@RequiredArgsConstructor
final class MediatRImpl implements MediatR {
    private final HandlerRegistry handlerRegistry;

    @Override
    public <TRequest extends Request<TResponse>, TResponse> TResponse send(@NonNull TRequest request) {
        log.trace("Sending request: " + request.getClass().getName());
        RequestHandler<TRequest, TResponse> handler = handlerRegistry.getRequestHandler(request.getClass());
        log.trace("Handling request: " + request.getClass().getName() + " with handler: " + handler.getClass().getName());
        return handler.handle(request);
    }

    @Override
    public <TRequest extends Request<TResponse>, TResponse> CompletableFuture<TResponse> sendAsync(@NonNull TRequest request) {
        log.trace("Sending async request: " + request.getClass().getName());
        RequestHandler<TRequest, TResponse> handler = handlerRegistry.getRequestHandler(request.getClass());
        log.trace("Handling async request: " + request.getClass().getName() + " with handler: " + handler.getClass().getName());
        return CompletableFuture.supplyAsync(() -> handler.handle(request));
    }

    @Override
    public <TEvent extends Event> void send(@NonNull TEvent event) {
        log.trace("Sending event: " + event.getClass().getName());
        List<EventHandler<TEvent>> handlers =  handlerRegistry.getEventHandler((Class<TEvent>) event.getClass());
        handlers.stream()
                .peek(handler ->  log.trace("Handling event: " + event.getClass().getName() + " with handler: " + handler.getClass().getName()))
                .forEach(handler -> handler.handle(event));
    }

    @Override
    public <TEvent extends Event> CompletableFuture<Void> sendAsync(@NonNull TEvent event) {
        log.trace("Sending event: " + event.getClass().getName());
        List<EventHandler<TEvent>> handlers =  handlerRegistry.getEventHandler((Class<TEvent>) event.getClass());
        return CompletableFuture.allOf(
                handlers.stream()
                    .peek(handler -> log.trace("Handling event: " + event.getClass().getName() + " with handler: " + handler.getClass().getName()))
                    .map(handler -> CompletableFuture.runAsync(() -> handler.handle(event)))
                .toArray(CompletableFuture[]::new)
        );
    }

}
