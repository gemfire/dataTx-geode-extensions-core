package io.pivotal.services.dataTx.geode.operations.stats.visitors;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;

import io.pivotal.services.dataTx.geode.operations.stats.*;
import io.pivotal.services.dataTx.geode.operations.stats.statInfo.ResourceInst;
import io.pivotal.services.dataTx.geode.operations.stats.statInfo.ResourceType;
import io.pivotal.services.dataTx.geode.operations.stats.statInfo.StatDescriptor;
import io.pivotal.services.dataTx.geode.operations.stats.statInfo.StatValue;
import nyla.solutions.core.io.IO;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GenericCsvStatsVisitorTest
{

	@Test
	public void testGenericCsvStatsVisitorFile()
	{
		String resourceTypeName = "junitResourceType";
		String resourceInstName = "junitResourceInstName";
		
		String expectFileName =  "target/stats/server1.gfs."+resourceTypeName+".csv";
		
		if(Paths.get(expectFileName).toFile().delete())
		{
			System.out.println("Old file deleleted");
		}
		
		File file = Paths.get("src/test/resources/stats/server1.gfs").toFile();
		
		GenericCsvStatsVisitor v = new GenericCsvStatsVisitor(file);
		v.setOutputDirectory(Paths.get("target/stats/"));
		
		StatValue stat1 = mock(StatValue.class);
		StatDescriptor stat1Descriptor = mock(StatDescriptor.class);
		when(stat1Descriptor.getName()).thenReturn("stat1");
		when(stat1.getSnapshotsMaximum()).thenReturn(Double.valueOf(10));
		when(stat1.getDescriptor()).thenReturn(stat1Descriptor);
		
		StatValue stat2 = mock(StatValue.class);

		StatDescriptor stat2Descriptor = mock(StatDescriptor.class);
		when(stat2Descriptor.getName()).thenReturn("stat2");
		
		when(stat2.getSnapshotsMaximum()).thenReturn(Double.valueOf(20));
		when(stat2.getDescriptor()).thenReturn(stat2Descriptor);

		ResourceType resourceType = mock(ResourceType.class);
		when(resourceType.getName()).thenReturn(resourceTypeName);
	
		v.visitResourceType(resourceType);
		
		ResourceInst resourceInst = mock(ResourceInst.class);
		when(resourceInst.getName()).thenReturn(resourceInstName);
		when(resourceInst.getType()).thenReturn(resourceType);
		when(resourceInst.getStatValue("stat1")).thenReturn(stat1);
		when(resourceInst.getStatValue("stat2")).thenReturn(stat2);
		
		when(resourceType.getStat("stat1")).thenReturn(stat1Descriptor);
		when(resourceType.getStat("stat2")).thenReturn(stat2Descriptor);
		
		StatValue[] statValues = { stat1,stat2};

		when(resourceInst.getStatValues()).thenReturn(statValues);
		v.visitResourceInst(resourceInst);
		
		assertTrue(Paths.get(expectFileName).toFile().exists());
	}

	@Test
	public void test_setoutputfile_for_multiple_stats()
			throws Exception
	{
		Paths.get("target/c1.csv").toFile().delete();
		Paths.get("target/c2.csv").toFile().delete();

		File statFile = new File("src/test/resources/stats/server1.gfs");
		GfStatsReader statsReader = new GfStatsReader(statFile);
		GenericCsvStatsVisitor v1 = new GenericCsvStatsVisitor(
				new File("target/c1.csv"));
		GenericCsvStatsVisitor v2 = new GenericCsvStatsVisitor(
				new File("target/c2.csv"));

		statsReader.acceptVisitors(v1, v2);

		assertTrue(IO.listFiles(Paths.get("target").toFile(),"c1.csv.*").length > 0);
		assertTrue(IO.listFiles(Paths.get("target").toFile(),"c2.csv.*").length > 0);


	}
}
