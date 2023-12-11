package io.github.lucasbuccilli.mediatr;

import lombok.NonNull;
import org.springframework.context.ApplicationContext;

/**
 * Factory to create a {@link io.github.lucasbuccilli.mediatr.MediatR}
 */
public class MediatRFactory {

    /**
     * @param applicationContext the context of the Spring application
     * @return {@link io.github.lucasbuccilli.mediatr.MediatR}
     */
    public static MediatR getMediatR(@NonNull ApplicationContext applicationContext) {
        return new MediatRImpl(new HandlerRegistry(applicationContext));
    }
}
