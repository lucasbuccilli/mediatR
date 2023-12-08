package io.github.lucasbuccilli.mediatr.integration.requests;


import io.github.lucasbuccilli.mediatr.Request;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TestRequest implements Request<String> {
    private Runnable runnable = () -> {};
    public void action() {
        runnable.run();
    }
};