package io.pivotal.services.dataTx.geode.functions;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Execution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class JvmExecutionFactoryTest
{
    @Mock
    Region<?, ?> region;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void onRegion()
    {
        JvmExecutionFactory jvmExecutionFactory = new JvmExecutionFactory();

        Execution exe = jvmExecutionFactory.onRegion(region);

        assertNotNull(exe);


    }
}