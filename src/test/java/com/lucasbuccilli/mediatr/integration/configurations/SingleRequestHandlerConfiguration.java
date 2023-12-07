package com.lucasbuccilli.mediatr.integration.configurations;

import com.lucasbuccilli.mediatr.RequestHandler;
import com.lucasbuccilli.mediatr.integration.requests.TestRequest;
import com.lucasbuccilli.mediatr.integration.requests.TestRequestHandler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SingleRequestHandlerConfiguration {
    @Bean
    public RequestHandler<TestRequest, String> singleHandler() {
        return new TestRequestHandler();
    }
}
