package com.lucasbuccilli.mediatr.integration.requests;

import com.lucasbuccilli.mediatr.RequestHandler;

public class TestRequestHandler implements RequestHandler<TestRequest, String> {

    @Override
    public String handle(TestRequest t) {
        t.action();
        return "Value";
    }
}
