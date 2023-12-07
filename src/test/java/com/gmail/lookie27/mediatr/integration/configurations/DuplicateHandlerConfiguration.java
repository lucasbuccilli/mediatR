package com.gmail.lookie27.mediatr.integration.configurations;

import com.gmail.lookie27.mediatr.infrastructure.RequestHandler;
import com.gmail.lookie27.mediatr.integration.models.TestRequest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DuplicateHandlerConfiguration {
    @Bean
    public RequestHandler<TestRequest, String> handlerOne() {
        return new RequestHandler<TestRequest, String>() {
            @Override
            public String handle(TestRequest t) {
                return "value";
            }
        };
    }

    @Bean
    public RequestHandler<TestRequest, String> handlerTwo() {
        return new RequestHandler<TestRequest, String>() {
            @Override
            public String handle(TestRequest t) {
                return "value";
            }
        };
    }
}