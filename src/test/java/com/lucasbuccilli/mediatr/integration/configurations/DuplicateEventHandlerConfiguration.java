package com.lucasbuccilli.mediatr.integration.configurations;

import com.lucasbuccilli.mediatr.EventHandler;
import com.lucasbuccilli.mediatr.integration.events.TestEvent;
import com.lucasbuccilli.mediatr.integration.events.TestEventHandler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DuplicateEventHandlerConfiguration {
    @Bean
    public EventHandler<TestEvent> handlerOne() {
        return new TestEventHandler();
    }

    @Bean
    public EventHandler<TestEvent> handlerTwo() {
        return new TestEventHandler();
    }
}