package io.github.lucasbuccilli.mediatr.integration.events;

import io.github.lucasbuccilli.mediatr.EventHandler;
import lombok.NonNull;

public class TestEventHandler implements EventHandler<TestEvent> {

    @Override
    public void handle(@NonNull TestEvent t) {
        t.action();
    }
}
