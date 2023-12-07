package com.lucasbuccilli.mediatr.integration.events;

import com.lucasbuccilli.mediatr.EventHandler;

public class TestEventHandler implements EventHandler<TestEvent> {

    @Override
    public void handle(TestEvent t) {
        t.action();
    }
}
