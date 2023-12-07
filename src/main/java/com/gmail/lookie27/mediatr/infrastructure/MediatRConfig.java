package com.gmail.lookie27.mediatr.infrastructure;

import com.gmail.lookie27.mediatr.implementation.HandlerRegistry;
import com.gmail.lookie27.mediatr.implementation.MediatRImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediatRConfig {
    @Bean
    public MediatR mediatR(ApplicationContext applicationContext) {
        return new MediatRImpl(new HandlerRegistry(applicationContext));
    }
}
