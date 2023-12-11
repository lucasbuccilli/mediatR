package io.github.lucasbuccilli.mediatr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediatRFactoryTest {
    @Test
    void nullCheckTests() {
        assertThrows(NullPointerException.class, () -> MediatRFactory.getMediatR(null));
    }

}