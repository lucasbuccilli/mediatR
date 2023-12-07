package com.gmail.lookie27.mediatr.infrastructure;

public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {
    TResponse handle(TRequest t);
}
