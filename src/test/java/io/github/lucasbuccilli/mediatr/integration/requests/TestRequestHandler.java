package io.github.lucasbuccilli.mediatr.integration.requests;

import io.github.lucasbuccilli.mediatr.RequestHandler;

public class TestRequestHandler implements RequestHandler<TestRequest, String> {

    @Override
    public String handle(TestRequest t) {
        t.action();
        return "Value";
    }
}
