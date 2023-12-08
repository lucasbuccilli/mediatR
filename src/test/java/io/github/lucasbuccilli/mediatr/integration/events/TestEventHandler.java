package io.github.lucasbuccilli.mediatr.integration.events;

import io.github.lucasbuccilli.mediatr.EventHandler;

public class TestEventHandler implements EventHandler<TestEvent> {

    @Override
    public void handle(TestEvent t) {
        t.action();
    }
}
