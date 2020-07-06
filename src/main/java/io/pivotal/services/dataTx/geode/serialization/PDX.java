package io.pivotal.services.dataTx.geode.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.geode.pdx.JSONFormatter;
import org.apache.geode.pdx.JSONFormatterException;
import org.apache.geode.pdx.PdxInstance;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author greg green
 * PDX conversion utility
 */
public class PDX
{
	/**
	 * JSON_TYPE_ATTRIBUTE = "@type"
	 */
	public static final String JSON_TYPE_ATTRIBUTE = "@type";

	public static String toJsonFromNondPdxObject(Object obj)
	{
		return new ToJsonFromNonPdxObject().convert(obj);
	}

	public static String toJSON(PdxInstance pdxInstance, String className)
	{

		String json = JSONFormatter.toJSON(pdxInstance);

		return addTypeToJson(json, className);

	}//-------------------------------------------
	static String addTypeToJson(String json, String type)
	{
		if(json.contains(JSON_TYPE_ATTRIBUTE))
			return json;

		String prefix = new StringBuilder().append("{\"")
				.append(PDX.JSON_TYPE_ATTRIBUTE)
				.append("\":\"")
				.append(type)
				.append("\", ").toString();

		json = json.replaceFirst("\\{",prefix);
		return json;
	}//-------------------------------------------
	public static PdxInstance fromJSON(String json)
	{
		try{



			 if(json == null || json.length() == 0)
			 	throw new IllegalArgumentException("json required");

			validateJson(json);

			return JSONFormatter.fromJSON(json);
		}
		catch(JSONFormatterException e){

			String message = e.getMessage();

			if(e.getCause() != null)
				message = message+" cause:"+e.getCause().getMessage();

			message += " json:"+json;

			throw new IllegalArgumentException(message);
		}
	}//-------------------------------------------

	public static void validateJson(String json)
	{
		if(!json.contains(JSON_TYPE_ATTRIBUTE))
			throw new IllegalArgumentException("Expected JSON to contain attribute:" + JSON_TYPE_ATTRIBUTE);

		try
		{
			new ObjectMapper().readTree(json);
		}
		catch(IOException e)
		{
			throw new IllegalArgumentException("Invalid json:"+json+" ERROR:"+e);
		}


	}

	public static SerializationPdxEntryWrapper toSerializePdxEntryWrapperFromJson(String json)
	{
		try
		{
			ObjectMapper om = new ObjectMapper();
			SerializationPdxEntryWrapper wrapper = om.readValue(json,SerializationPdxEntryWrapper.class);
			return wrapper;

		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}


	}//-------------------------------------------

	/**
	 *
	 * @param key the Region key
	 * @param pdxInstance the region value PDX
	 * @param <Key> the key type
	 * @param valueClassName the value class name
	 * @return the wrapper object
	 */
	public static <Key extends Serializable> SerializationPdxEntryWrapper toSerializePdxEntryWrapper(Key key, String valueClassName, PdxInstance pdxInstance)
	{
		return new SerializationPdxEntryWrapper(key,valueClassName,pdxInstance);
	}

	public static PdxInstance fromObject(Object obj)
	{
		if(obj instanceof PdxInstance)
			return (PdxInstance)obj;


		try
		{

			String json = toJsonFromNondPdxObject(obj);

			return JSONFormatter.fromJSON(json);

		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

	}//-------------------------------------------


}
