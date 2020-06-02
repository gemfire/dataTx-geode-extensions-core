package io.pivotal.services.dataTx.geode.functions;

import java.io.Serializable;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionService;


/**
 * @author Gregory Green
 *
 */
public class CacheRegionDictionary implements RegionDictionary, Serializable
{

	private final RegionService regionService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9067270223076402385L;

	public CacheRegionDictionary(RegionService regionService)
	{
		this.regionService = regionService;
	}


	@Override
	public <K, V> Region<K, V> getRegion(String name)
	{
		return this.regionService.getRegion(name);
	}

}
