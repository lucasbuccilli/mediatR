package com.lucasbuccilli.mediatr.integration.events;


import com.lucasbuccilli.mediatr.Event;
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