package com.lucasbuccilli.mediatr.integration.configurations;

import com.lucasbuccilli.mediatr.MediatR;
import com.lucasbuccilli.mediatr.MediatRFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MediatRConfiguration {
    @Bean
    public MediatR mediatR(ApplicationContext applicationContext) {
        return MediatRFactory.getMediatR(applicationContext);
    }
}
