package org.msharma.presentation.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author Mohan Sharma Created on 26/01/18.
 */
public class CustomObjectMapper
{
	public String dataToJson(Object data) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			return sw.toString();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
