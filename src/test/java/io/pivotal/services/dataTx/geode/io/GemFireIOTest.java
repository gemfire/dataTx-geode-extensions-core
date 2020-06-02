package io.pivotal.services.dataTx.geode.io;

import org.apache.geode.cache.execute.ResultSender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GemFireIOTest
{

    @Test
    void exeWithResults_send_exception_with_runtime()
    {

        ResultSender<Object> resultSender = mock(ResultSender.class);
        assertTrue(GemFireIO.isErrorAndSendException(resultSender,new RuntimeException()));
        verify(resultSender).sendException(any(RuntimeException.class));

    }

    @Test
    void test_no_exception()
    {
        ResultSender<Object> resultSender = mock(ResultSender.class);
        assertFalse(GemFireIO.isErrorAndSendException(resultSender,"hello"));
        verify(resultSender,never()).sendException(any(RuntimeException.class));
    }
}