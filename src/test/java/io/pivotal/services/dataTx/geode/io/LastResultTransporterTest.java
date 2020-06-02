package io.pivotal.services.dataTx.geode.io;

import org.apache.geode.cache.execute.ResultSender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LastResultTransporterTest
{

    @Test
    void send()
    {
        LastResultTransporter transporter = new LastResultTransporter();
        ResultSender<Object> resultSender = mock(ResultSender.class);
        transporter.send(resultSender,"data");
        verify(resultSender).lastResult(any());

    }

    @Test
    void not_send_excpetion()
    {
        LastResultTransporter transporter = new LastResultTransporter();
        ResultSender<Object> resultSender = mock(ResultSender.class);
        transporter.send(resultSender,new RuntimeException());
        verify(resultSender,never()).lastResult(any());

    }
}