package io.pivotal.services.dataTx.geode.functions;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionService;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

/**
 * Use the client cache to obtain region objects
 * @author Gregory Green
 *
 */
public class ClientRegionDictionary extends CacheRegionDictionary
{
	public ClientRegionDictionary(RegionService regionService)
	{
		super(regionService);
	}
}
