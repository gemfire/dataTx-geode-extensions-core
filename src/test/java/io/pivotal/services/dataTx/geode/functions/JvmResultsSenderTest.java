package io.pivotal.services.dataTx.geode.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JvmResultsSenderTest
{
    private JvmResultsSender<String> jvmResultsSender;

    @BeforeEach
    public void setUp()
    {
        jvmResultsSender = new JvmResultsSender<>();
    }

    @Test
    void sendResult()
    {
        jvmResultsSender.sendResult("test");

        assertTrue(jvmResultsSender.getResults() != null);
        assertTrue(!jvmResultsSender.getResults().isEmpty());
        assertEquals("test",jvmResultsSender.getResults().iterator().next());

    }

    @Test
    void lastResult()
    {
        jvmResultsSender.lastResult("last");

        assertTrue(jvmResultsSender.getResults() != null);
        assertTrue(!jvmResultsSender.getResults().isEmpty());
        assertEquals("last",jvmResultsSender.getResults().iterator().next());

    }

    @Test
    void sendException()
    {
        assertThrows(RuntimeException.class,
                () -> jvmResultsSender.sendException(new RuntimeException()));

    }

    @Test
    void getResults()
    {
        assertNotNull(jvmResultsSender.getResults());
        assertTrue(jvmResultsSender.getResults().isEmpty());
    }
}