package io.github.lucasbuccilli.mediatr.integration.requests;

import io.github.lucasbuccilli.mediatr.RequestHandler;
import lombok.NonNull;

public class TestRequestHandler implements RequestHandler<TestRequest, String> {

    @Override
    public String handle(@NonNull TestRequest t) {
        t.action();
        return "Value";
    }
}
