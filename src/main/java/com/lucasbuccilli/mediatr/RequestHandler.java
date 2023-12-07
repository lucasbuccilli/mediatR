package com.lucasbuccilli.mediatr;

public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {
    TResponse handle(TRequest t);
}
