package com.university.cliTest;

import com.university.cli.EntityException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CLITest {
    @Test
    void testExceptionMessage() {
        EntityException exception = new EntityException("Entity not found");
        assertEquals("Entity not found", exception.getMessage());
    }

    @Test
    void testExceptionInheritance() {
        EntityException exception = new EntityException("Test message");
        assertTrue(exception instanceof RuntimeException);
    }
}
