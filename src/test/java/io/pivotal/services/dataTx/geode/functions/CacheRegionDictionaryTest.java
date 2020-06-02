package io.pivotal.services.dataTx.geode.functions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.geode.cache.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CacheRegionDictionaryTest
{
    @Mock
    RegionService regionService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_basic()
    {
        CacheRegionDictionary dictionary = new CacheRegionDictionary(regionService);

        dictionary.getRegion("test");
        verify(regionService).getRegion(anyString());
    }

}