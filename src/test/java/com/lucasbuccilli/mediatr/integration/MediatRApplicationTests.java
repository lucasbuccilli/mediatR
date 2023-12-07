package com.lucasbuccilli.mediatr.integration;

import com.lucasbuccilli.mediatr.Event;
import com.lucasbuccilli.mediatr.EventHandler;
import com.lucasbuccilli.mediatr.MediatR;
import com.lucasbuccilli.mediatr.Request;
import com.lucasbuccilli.mediatr.RequestHandler;
import com.lucasbuccilli.mediatr.exceptions.DuplicateHandlerException;
import com.lucasbuccilli.mediatr.exceptions.MissingHandlerException;
import com.lucasbuccilli.mediatr.integration.configurations.DuplicateEventHandlerConfiguration;
import com.lucasbuccilli.mediatr.integration.configurations.DuplicateRequestHandlerConfiguration;
import com.lucasbuccilli.mediatr.integration.configurations.MediatRConfiguration;
import com.lucasbuccilli.mediatr.integration.configurations.SingleEventHandlerConfiguration;
import com.lucasbuccilli.mediatr.integration.configurations.SingleRequestHandlerConfiguration;
import com.lucasbuccilli.mediatr.integration.events.TestEvent;
import com.lucasbuccilli.mediatr.integration.requests.TestRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.springframework.core.NestedExceptionUtils.getRootCause;

class MediatRApplicationTests {
    @Nested
    class ContextTests {
        @Test
        void duplicateRequestHandlersShouldThrowException() {
            new ApplicationContextRunner()
                    .withUserConfiguration(DuplicateRequestHandlerConfiguration.class)
                    .withUserConfiguration(MediatRConfiguration.class)
                    .run(context -> {
                        assertNotNull(context.getStartupFailure());
                        var rootCause = getRootCause(context.getStartupFailure());
                        assertNotNull(rootCause);
                        assertEquals(DuplicateHandlerException.class, rootCause.getClass());
                    });
        }

        @Test
        void duplicateEventHandlersShouldThrowException() {
            new ApplicationContextRunner()
                    .withUserConfiguration(DuplicateEventHandlerConfiguration.class)
                    .withUserConfiguration(MediatRConfiguration.class)
                    .run(context -> {
                        assertNotNull(context.getStartupFailure());
                        var rootCause = getRootCause(context.getStartupFailure());
                        assertNotNull(rootCause);
                        assertEquals(DuplicateHandlerException.class, rootCause.getClass());
                    });
        }
    }

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @Import(value = { SingleRequestHandlerConfiguration.class, MediatRConfiguration.class })
    class RequestHandlerTests {
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
        void shouldDelegateAsyncRequestToHandler() throws ExecutionException, InterruptedException {
            var sleepTime = 100;
            var request = new TestRequest(() -> {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            var future = mediatR.sendAsync(request);
            assertFalse(future.isDone());
            future.get();
            assertTrue(future.isDone());
        }

        @Test
        void shouldThrowExceptionWhenRequestHandlerNotRegistered() {
            var exception = assertThrows(MissingHandlerException.class, () -> mediatR.send(new Request<Object>() {}));
            assertEquals(MissingHandlerException.class, exception.getClass());
        }
    }



    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @Import(value = { SingleEventHandlerConfiguration.class, MediatRConfiguration.class })
    class EventHandlerTests {
        @Autowired
        private MediatR mediatR;

        @SpyBean
        private EventHandler<TestEvent> testHandler;

        @Test
        void shouldDelegateEventToHandler() {
            var event = new TestEvent();
            mediatR.send(event);
            verify(testHandler).handle(event);
        }

        @Test
        void shouldDelegateAsyncEventToHandler() throws ExecutionException, InterruptedException {
            var sleepTime = 100;
            var event = new TestEvent(() -> {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            var future = mediatR.sendAsync(event);
            assertFalse(future.isDone());
            future.get();
            assertTrue(future.isDone());
        }

        @Test
        void shouldThrowExceptionWhenEventHandlerNotRegistered() {
            var exception = assertThrows(MissingHandlerException.class, () -> mediatR.send(new Event(){}));
            assertEquals(MissingHandlerException.class, exception.getClass());
        }
    }
}
