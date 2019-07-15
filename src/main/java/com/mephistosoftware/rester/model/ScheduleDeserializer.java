package com.mephistosoftware.rester.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ScheduleDeserializer extends StdDeserializer<List<Schedule>>{
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScheduleDeserializer() {
        this(null);
    }
 
    public ScheduleDeserializer(Class<?> vc) {
        super(vc);
    }
 
    @Override
    public List<Schedule> deserialize(
      JsonParser jsonparser, 
      DeserializationContext context) 
      throws IOException, JsonProcessingException {
         
        return new ArrayList<>();
    }

}