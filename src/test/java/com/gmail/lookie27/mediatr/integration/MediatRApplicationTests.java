package com.gmail.lookie27.mediatr.integration;

import com.gmail.lookie27.mediatr.infrastructure.MediatR;
import com.gmail.lookie27.mediatr.infrastructure.MediatRConfig;
import com.gmail.lookie27.mediatr.infrastructure.Request;
import com.gmail.lookie27.mediatr.infrastructure.RequestHandler;
import com.gmail.lookie27.mediatr.infrastructure.exceptions.DuplicateHandlerException;
import com.gmail.lookie27.mediatr.infrastructure.exceptions.MissingHandlerException;
import com.gmail.lookie27.mediatr.integration.configurations.DuplicateHandlerConfiguration;
import com.gmail.lookie27.mediatr.integration.configurations.SingleHandlerConfiguration;
import com.gmail.lookie27.mediatr.integration.models.TestRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.springframework.core.NestedExceptionUtils.getRootCause;

class MediatRApplicationTests {
    @Nested
    class ContextTests {
        @Test
        void duplicateHandlersShouldThrowException() {
            new ApplicationContextRunner()
                    .withUserConfiguration(DuplicateHandlerConfiguration.class)
                    .withUserConfiguration(MediatRConfig.class)
                    .run(context -> {
                        assertNotNull(context.getStartupFailure());
                        var rootCause = getRootCause(context.getStartupFailure());
                        assertNotNull(rootCause);
                        assertEquals(DuplicateHandlerException.class, rootCause.getClass());
                    });
        }
    }

    @Nested
    @SpringBootTest(classes = SingleHandlerConfiguration.class)
    class HandlerTests {
        @Autowired
        private MediatR mediatR;

        @SpyBean
        private RequestHandler<TestRequest, String> testHandler;

        @Test
        void shouldDelegateRequestToHandler() {
            var request = new TestRequest();
            mediatR.send(request);
            verify(testHandler).handle(request);
        }

        @Test
        void shouldThrowExceptionWhenHandlerNotRegistered() {
            var exception = assertThrows(MissingHandlerException.class, () -> mediatR.send(new Request<Object>() {}));
            assertEquals(MissingHandlerException.class, exception.getClass());
        }
    }
}
