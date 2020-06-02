package io.pivotal.services.dataTx.geode.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class JvmResultCollectorTest
{
    @Mock
    JvmResultsSender resultSender;

    JvmResultCollector<?,?> jvmResultCollector;
    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        jvmResultCollector = new JvmResultCollector<>(this.resultSender);
    }

    @Test
    void getResult()
    {
        jvmResultCollector.getResult();
        verify(resultSender).getResults();

    }

    @Test
    void testGetResult()
    throws InterruptedException
    {
        jvmResultCollector.getResult(1L, TimeUnit.DAYS);
        verify(resultSender).getResults();
    }
}