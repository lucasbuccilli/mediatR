package com.lucasbuccilli.mediatr;

import org.springframework.context.ApplicationContext;

public class MediatRFactory {

    public static MediatR getMediatR(ApplicationContext applicationContext) {
        return new MediatRImpl(new HandlerRegistry(applicationContext));
    }
}
