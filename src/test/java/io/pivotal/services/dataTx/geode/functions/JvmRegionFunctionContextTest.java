package io.pivotal.services.dataTx.geode.functions;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.ResultSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JvmRegionFunctionContextTest
{
    @Mock
    Cache cache;
    JvmRegionFunctionContext<?,?,?> jvmRegionFunctionContext;

    @Mock
    private Region<? extends Object, ? extends Object> dataSet;

    @Mock
    private ResultSender<? extends Object> resultSender;

    private Object arguments = "sds";
    private Set<?> filter = Collections.singleton("sdsds");

    private JvmRegionFunctionContext<?,?,?> target;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        this.jvmRegionFunctionContext = new JvmRegionFunctionContext<>(cache);
        target = new JvmRegionFunctionContext<>(
                this.cache,dataSet,resultSender,arguments, filter);
    }

    @Test
    void getArguments()
    {

        assertNull(this.jvmRegionFunctionContext.getArguments());
    }

    @Test
    void getFunctionId()
    {
        assertNull(this.jvmRegionFunctionContext.getFunctionId());

        String expected ="abc";
        this.target.setFunctionId(expected);
        assertEquals(expected,target.getFunctionId());
    }

    @Test
    void getResultSender()
    {
        assertNull(this.jvmRegionFunctionContext.getResultSender());
        assertEquals(this.resultSender,target.getResultSender());

    }

    @Test
    void isPossibleDuplicate()
    {
        assertFalse(this.jvmRegionFunctionContext.isPossibleDuplicate());
    }

    @Test
    void getFilter()
    {
        assertNull(this.jvmRegionFunctionContext.getFilter());
        assertEquals(this.filter,target.getFilter());
    }


    @Test
    void getDataSet()
    {
        assertNull(this.jvmRegionFunctionContext.getDataSet());
        assertEquals(this.dataSet,target.getDataSet());
    }
}