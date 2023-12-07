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

    public HandlerRegistry(ApplicationContext context) {
        applicationContext = context;
        registerHandlers();
    }

    public <TRequest extends Request<TResponse>, TResponse> RequestHandler<TRequest, TResponse> get(Class<TRequest> requestClass) {
        if (!requestHandlerRegistry.containsKey(requestClass)) {
            throw new MissingHandlerException((Class<Request<?>>) requestClass);
        }
        return (RequestHandler<TRequest, TResponse>) requestHandlerRegistry.get(requestClass).get();
    }

    private void registerHandlers() {
        Arrays.stream(applicationContext.getBeanNamesForType(RequestHandler.class))
                .forEach(this::registerRequestHandler);
    }

    private <TResponse> void registerRequestHandler(String requestHandlerName) {
        RequestHandler<Request<TResponse>, TResponse> handler = (RequestHandler<Request<TResponse>, TResponse>) applicationContext.getBean(requestHandlerName);
        Class<?>[] genericArguments = GenericTypeResolver.resolveTypeArguments(handler.getClass(), RequestHandler.class);
        if (genericArguments != null) {
            Class<Request<?>> requestType = (Class<Request<?>>) genericArguments[0];
            log.trace("Registering EventHandler for event: " + requestType.getName());
            if (requestHandlerRegistry.containsKey(requestType)) {
                throw new DuplicateHandlerException(requestType);
            }
            requestHandlerRegistry.put(requestType, () -> applicationContext.getBean(handler.getClass()));
        }
    }
}
