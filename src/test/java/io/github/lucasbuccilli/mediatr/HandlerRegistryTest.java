package io.github.lucasbuccilli.mediatr;

import io.github.lucasbuccilli.mediatr.exceptions.InvalidHandlerException;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HandlerRegistryTest {
    private HandlerRegistry handlerRegistry;
    private ApplicationContext applicationContext;

    @BeforeEach
    void beforeEach() {
        applicationContext = mock(ApplicationContext.class);
    }

    @Test
    void shouldInvalidHandlerWhenInvalidRequestHandler() {
        var handler = new RequestHandler() {
            @Override
            public Object handle(@NonNull Request request) {
                return null;
            }
        };

        when(applicationContext.getBeanNamesForType(RequestHandler.class)).thenReturn(new String[]{"handler"});
        when(applicationContext.getBeanNamesForType(EventHandler.class)).thenReturn(new String[]{});
        when(applicationContext.getBean("handler")).thenReturn(handler);

        assertThrows(InvalidHandlerException.class, () -> handlerRegistry = new HandlerRegistry(applicationContext));
    }

    @Test
    void shouldInvalidHandlerWhenInvalidEventHandler() {
        var handler = new EventHandler() {
            @Override
            public void handle(@NonNull Event event) {
            }
        };

        when(applicationContext.getBeanNamesForType(EventHandler.class)).thenReturn(new String[]{"handler"});
        when(applicationContext.getBeanNamesForType(RequestHandler.class)).thenReturn(new String[]{});
        when(applicationContext.getBean("handler")).thenReturn(handler);

        assertThrows(InvalidHandlerException.class, () -> handlerRegistry = new HandlerRegistry(applicationContext));
    }

}