package com.gmail.lookie27.mediatr.infrastructure;

public interface MediatR {
    <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest r);
}
