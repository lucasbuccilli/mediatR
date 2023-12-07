package com.lucasbuccilli.mediatr;

import java.util.concurrent.CompletableFuture;

public interface MediatR {
    <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest r);

    <TRequest extends Request<TResponse>, TResponse> CompletableFuture<TResponse> sendAsync(TRequest r);
}
