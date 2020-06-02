package io.pivotal.services.dataTx.geode.functions;

import static org.mockito.Mockito.*;

import org.apache.geode.cache.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientRegionDictionaryTest
{
    @Mock
    RegionService regionService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getRegion()
    {

        ClientRegionDictionary dictionary = new ClientRegionDictionary(regionService);
        dictionary.getRegion("name");
        verify(regionService).getRegion(anyString());

    }

}