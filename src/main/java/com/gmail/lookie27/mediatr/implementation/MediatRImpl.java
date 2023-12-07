package com.gmail.lookie27.mediatr.implementation;

import com.gmail.lookie27.mediatr.infrastructure.MediatR;
import com.gmail.lookie27.mediatr.infrastructure.Request;
import com.gmail.lookie27.mediatr.infrastructure.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MediatRImpl implements MediatR {
    private final HandlerRegistry handlerRegistry;

    @Override
    public <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest request) {
        log.trace("Sending event: " + request.getClass().getName());
        RequestHandler<TRequest, TResponse> handler = handlerRegistry.get(request.getClass());
        log.trace("Handling event: " + request.getClass().getName() + " with handler: " + handler.getClass().getName());
        return handler.handle(request);
    }
}
