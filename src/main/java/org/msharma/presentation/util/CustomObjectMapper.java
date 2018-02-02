package org.msharma.presentation.util;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.msharma.presentation.dto.StudentDTO;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author Mohan Sharma Created on 26/01/18.
 */
public class CustomObjectMapper
{
	final static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public String dataToJson(Object data) {
		try
		{
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			return sw.toString();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public StudentDTO jsonToData(String json) {
		try
		{
			return mapper.readValue(json, StudentDTO.class);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
