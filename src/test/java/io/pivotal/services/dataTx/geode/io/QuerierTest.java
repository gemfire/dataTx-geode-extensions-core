package io.pivotal.services.dataTx.geode.io;

import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.query.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


class QuerierTest
{
    @Mock
    Query queryObject;

    @Mock
    RegionFunctionContext rfc;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void query_no_rfc()
    throws NameResolutionException, TypeMismatchException, QueryInvocationTargetException, FunctionDomainException
    {

        Object[] params = {};

        Querier.query(queryObject,null,params);

        verify(queryObject).execute();
    }

    @Test
    void query_with_rfc()
    throws NameResolutionException, TypeMismatchException, QueryInvocationTargetException, FunctionDomainException
    {
        Object[] params = {};

        Querier.query(queryObject,rfc,params);

        verify(queryObject).execute(rfc);
    }
}