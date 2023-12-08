package io.github.lucasbuccilli.mediatr.integration.configurations;

import io.github.lucasbuccilli.mediatr.MediatR;
import io.github.lucasbuccilli.mediatr.MediatRFactory;
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
