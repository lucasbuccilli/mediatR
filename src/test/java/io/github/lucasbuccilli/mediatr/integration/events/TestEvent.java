package io.github.lucasbuccilli.mediatr.integration.events;


import io.github.lucasbuccilli.mediatr.Event;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TestEvent implements Event {
    private Runnable runnable = () -> {};
    public void action() {
        runnable.run();
    }
};