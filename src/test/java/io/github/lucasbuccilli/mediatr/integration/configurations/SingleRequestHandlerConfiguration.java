package io.github.lucasbuccilli.mediatr.integration.configurations;

import io.github.lucasbuccilli.mediatr.RequestHandler;
import io.github.lucasbuccilli.mediatr.integration.requests.TestRequest;
import io.github.lucasbuccilli.mediatr.integration.requests.TestRequestHandler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SingleRequestHandlerConfiguration {
    @Bean
    public RequestHandler<TestRequest, String> singleHandler() {
        return new TestRequestHandler();
    }
}
