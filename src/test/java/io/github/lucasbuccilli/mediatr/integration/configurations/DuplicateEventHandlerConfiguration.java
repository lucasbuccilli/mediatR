package io.github.lucasbuccilli.mediatr.integration.configurations;

import io.github.lucasbuccilli.mediatr.EventHandler;
import io.github.lucasbuccilli.mediatr.integration.events.TestEvent;
import io.github.lucasbuccilli.mediatr.integration.events.TestEventHandler;
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