[![Maven Central](https://img.shields.io/maven-central/v/io.github.lucasbuccilli/mediatR.svg?label=Maven%20Central)](https://central.sonatype.com/namespace/io.github.lucasbuccilli)
[![Build](https://img.shields.io/github/actions/workflow/status/lucasbuccilli/mediatR/test.yml?label=Build)](https://github.com/lucasbuccilli/mediatR/actions/workflows/test.yml?label=Build)
![Test Coverage](https://github.com/lucasbuccilli/mediatR/blob/gh-pages/badges/jacoco.svg?raw=true)
# MediatR
Simple mediator implementation in Java.

Greatly inspired by [jbogard](https://github.com/jbogard/MediatR)'s .NET implementation.


## MediatR Configuration

Add MediatR to your _build.gradle_
```
implementation 'io.github.lucasbuccilli:mediatR:1.0.2'
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

MediatR has two kind of messages it dispatches:

**Request/response messages**, dispatched to a single handler
- `Request<T>`
- `RequestHandler<T extends Request>`

**Event messages**, dispatched to multiple handlers
- `Event`
- `EventHandler<T extends Event>`

### Requests and Request Handlers
Create a request
```
public class GetUserRequest implements Request<User> {
    private int userId;
    ...
}
```

Create a handler

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
Send the request with MediatR and _tada_
```
var user = mediatR.send(new GetUserRequest(1));
```
### Events and Event Handlers
Events are similar to requests, but you can register multiple handlers for the same event.

```
public class DeleteUserEvent implements Event {
    private int userId;
    ...
}
```
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
```
mediatR.send(new DeleteUserEvent(1));
```


### Async
MediatR also supports asynchronous operations

```
CompletableFuture<User> userFurure = mediatR.sendAsync(new GetUserRequest(1));

CompletableFuture<Void> userFurure = mediatR.sendAsync(new DeleteUserEvent(1));
```