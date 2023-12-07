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
        log.trace("Sending event: " + request.getClass().getName());
        RequestHandler<TRequest, TResponse> handler = handlerRegistry.get(request.getClass());
        log.trace("Handling event: " + request.getClass().getName() + " with handler: " + handler.getClass().getName());
        return handler.handle(request);
    }

    @Override
    public <TRequest extends Request<TResponse>, TResponse> CompletableFuture<TResponse> sendAsync(TRequest request) {
        log.trace("Sending async event: " + request.getClass().getName());
        RequestHandler<TRequest, TResponse> handler = handlerRegistry.get(request.getClass());
        log.trace("Handling async event: " + request.getClass().getName() + " with handler: " + handler.getClass().getName());
        return CompletableFuture.supplyAsync(() -> handler.handle(request));
    }
}
