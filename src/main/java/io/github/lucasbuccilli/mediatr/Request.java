package io.github.lucasbuccilli.mediatr;

/**
 * Request to be handled by a {@link io.github.lucasbuccilli.mediatr.RequestHandler}
 * @param <T> Response type that the {@link io.github.lucasbuccilli.mediatr.RequestHandler} will return
 */
public interface Request<T> extends Message {
}
