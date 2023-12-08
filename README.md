# MediatR
Simple mediator implementation in Java.

In-process messaging with no dependencies.

Greatly inspired by [jbogard](https://github.com/jbogard/MediatR)'s .NET implementation.


## MediatR Configuration

Add MediatR to your _build.gradle_
```
implementation 'io.github.lucasbuccilli:mediatR:1.0.0'
```

Create a MediatR bean
```
@Configuration
public class MediatRConfig {
    @Bean
    public MediatR mediatR(ApplicationContext applicationContext) {
        return MediatRFactory.getMediatR(applicationContext);
    }
}
```

## Using MediatR

MediatR has two kinds of messages it dispatches:

**Request/response messages**, dispatched to a single handler
- `Request<T>`
- `RequestHandler<T extends Request>`

**Event messages**, dispatched to multiple handlers
- `Event`
- `EventHandler<T extends Event>`

### Requests and Request Handlers
First, create a request
```
public class GetUserRequest implements Request<User> {
    private int userId;
    ...
}
```
\
\
Next, create a handler

__Note: A handler must be annotated with `@Component` or `@Service`__

```
@Component
public class GetUserRequestHandler implements RequestHandler<GetUserRequest> {
    @Override
    public User handle(GetUserRequest request) {
        ...
        return user;
    }
}
```
\
\
Now send the request with MediatR
```
var user = mediatR.send(new GetUserRequest(1));
```

Sending events are similar, but you can register multiple handlers for the same event.

```
public class DeleteUserEvent implements Event {
    private int userId;
    ...
}
```
\
\
Next, create a handler

__Note: A handler must be annotated with `@Component` or `@Service`__

```
@Component
public class DeleteUserEventHandlerOne implements EventHandler<DeleteUserEvent> {
    @Override
    public void handle(DeleteUserEvent event) {
        ...
    }
}

@Component
public class DeleteUserEventHandlerTwo implements EventHandler<DeleteUserEvent> {
    @Override
    public void handle(DeleteUserEvent event) {
        ...
    }
}
```
\
\
Now send the request with MediatR
```
mediatR.send(new DeleteUserEvent(1));
```


### Async
MediatR also supports asynchronous operations

```
CompletableFuture<User> userFurure = mediatR.sendAsync(new GetUserRequest(1));

CompletableFuture<Void> userFurure = mediatR.sendAsync(new DeleteUserEvent(1));
```

For events, the future will complete when all handlers have finished.