package com.lucasbuccilli.mediatr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
class MediatRImpl implements MediatR {
    private final HandlerRegistry handlerRegistry;

    @Override
    public <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest request) {
        log.trace("Sending request: " + request.getClass().getName());
        RequestHandler<TRequest, TResponse> handler = handlerRegistry.getRequestHandler(request.getClass());
        log.trace("Handling request: " + request.getClass().getName() + " with handler: " + handler.getClass().getName());
        return handler.handle(request);
    }

    @Override
    public <TRequest extends Request<TResponse>, TResponse> CompletableFuture<TResponse> sendAsync(TRequest request) {
        log.trace("Sending async request: " + request.getClass().getName());
        RequestHandler<TRequest, TResponse> handler = handlerRegistry.getRequestHandler(request.getClass());
        log.trace("Handling async request: " + request.getClass().getName() + " with handler: " + handler.getClass().getName());
        return CompletableFuture.supplyAsync(() -> handler.handle(request));
    }

    @Override
    public <TEvent extends Event> void send(TEvent event) {
        log.trace("Sending event: " + event.getClass().getName());
        EventHandler<TEvent> handler = (EventHandler<TEvent>) handlerRegistry.getEventHandler(event.getClass());
        log.trace("Handling event: " + event.getClass().getName() + " with handler: " + handler.getClass().getName());
        handler.handle(event);
    }

    @Override
    public <TEvent extends Event> CompletableFuture<Void> sendAsync(TEvent event) {
        log.trace("Sending event: " + event.getClass().getName());
        EventHandler<TEvent> handler = (EventHandler<TEvent>) handlerRegistry.getEventHandler(event.getClass());
        log.trace("Handling event: " + event.getClass().getName() + " with handler: " + handler.getClass().getName());
        return CompletableFuture.runAsync(() -> handler.handle(event));
    }

}
