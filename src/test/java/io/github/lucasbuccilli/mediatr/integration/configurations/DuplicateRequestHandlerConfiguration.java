package io.github.lucasbuccilli.mediatr.integration.configurations;

import io.github.lucasbuccilli.mediatr.RequestHandler;
import io.github.lucasbuccilli.mediatr.integration.requests.TestRequestHandler;
import io.github.lucasbuccilli.mediatr.integration.requests.TestRequest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DuplicateRequestHandlerConfiguration {
    @Bean(name = "handlerOne")
    public RequestHandler<TestRequest, String> handlerOne() {
        return new TestRequestHandler();
    }

    @Bean(name = "handlerTwo")
    public RequestHandler<TestRequest, String> handlerTwo() {
        return new TestRequestHandler();
    }
}