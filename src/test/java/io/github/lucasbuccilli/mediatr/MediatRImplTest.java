package io.github.lucasbuccilli.mediatr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MediatRImplTest {
    @Test
    void nullCheckTests() {
        var mediatR = new MediatRImpl(null);

        assertThrows(NullPointerException.class, () -> mediatR.send((Event) null));
        assertThrows(NullPointerException.class, () -> mediatR.sendAsync((Event) null));
        assertThrows(NullPointerException.class, () -> mediatR.send((Request) null));
        assertThrows(NullPointerException.class, () -> mediatR.sendAsync((Request) null));
    }

}