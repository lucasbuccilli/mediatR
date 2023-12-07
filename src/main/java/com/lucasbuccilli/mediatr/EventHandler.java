package com.lucasbuccilli.mediatr;

public interface EventHandler<TRequest extends Event> {
    void handle(TRequest t);
}
