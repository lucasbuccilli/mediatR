package com.lucasbuccilli.mediatr;

import com.lucasbuccilli.mediatr.exceptions.DuplicateHandlerException;
import com.lucasbuccilli.mediatr.exceptions.MissingHandlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
class HandlerRegistry {
    private final ApplicationContext applicationContext;
    private final Map<Class<Request<?>>, Supplier<RequestHandler<Request<?>, ?>>> requestHandlerRegistry = new HashMap<>();
    private final Map<Class<Event>, Supplier<EventHandler<Event>>> eventHandlerRegistry = new HashMap<>();

    public HandlerRegistry(ApplicationContext context) {
        applicationContext = context;
        registerHandlers();
    }

    public <TRequest extends Request<TResponse>, TResponse> RequestHandler<TRequest, TResponse> getRequestHandler(Class<TRequest> requestClass) {
        if (!requestHandlerRegistry.containsKey(requestClass)) {
            throw new MissingHandlerException(requestClass);
        }
        return (RequestHandler<TRequest, TResponse>) requestHandlerRegistry.get(requestClass).get();
    }

    public <TRequest extends Event> EventHandler<TRequest> getEventHandler(Class<TRequest> requestClass) {
        if (!eventHandlerRegistry.containsKey(requestClass)) {
            throw new MissingHandlerException(requestClass);
        }
        return (EventHandler<TRequest>) eventHandlerRegistry.get(requestClass).get();
    }

    private void registerHandlers() {
        Arrays.stream(applicationContext.getBeanNamesForType(RequestHandler.class))
                .forEach(this::registerRequestHandler);
        Arrays.stream(applicationContext.getBeanNamesForType(EventHandler.class))
                .forEach(this::registerEventHandler);
    }

    private <TResponse> void registerRequestHandler(String requestHandlerName) {
        RequestHandler<Request<TResponse>, TResponse> handler = (RequestHandler<Request<TResponse>, TResponse>) applicationContext.getBean(requestHandlerName);
        Class<?>[] genericArguments = GenericTypeResolver.resolveTypeArguments(handler.getClass(), RequestHandler.class);
        if (genericArguments != null) {
            Class<Request<?>> requestType = (Class<Request<?>>) genericArguments[0];
            log.trace("Registering RequestHandler for event: " + requestType.getName());
            if (requestHandlerRegistry.containsKey(requestType)) {
                throw new DuplicateHandlerException(requestType);
            }
            requestHandlerRegistry.put(requestType, () -> applicationContext.getBean(handler.getClass()));
        }
    }

    private <TResponse> void registerEventHandler(String eventHandlerName) {
        EventHandler<Event> handler = (EventHandler<Event>) applicationContext.getBean(eventHandlerName);
        Class<?>[] genericArguments = GenericTypeResolver.resolveTypeArguments(handler.getClass(), EventHandler.class);
        if (genericArguments != null) {
            Class<Event> eventClass = (Class<Event>) genericArguments[0];
            log.trace("Registering EventHandler for event: " + eventClass.getName());
            if (eventHandlerRegistry.containsKey(eventClass)) {
                throw new DuplicateHandlerException(eventClass);
            }
            eventHandlerRegistry.put(eventClass, () -> applicationContext.getBean(handler.getClass()));
        }
    }
}
