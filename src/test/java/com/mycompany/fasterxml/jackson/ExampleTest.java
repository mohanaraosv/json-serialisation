package com.mycompany.fasterxml.jackson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.mycompany.fasterxml.jackson.model.Event;
import com.mycompany.fasterxml.jackson.model.Example;
import com.mycompany.fasterxml.jackson.model.Metadata;
import com.mycompany.fasterxml.jackson.model.Source;

public class ExampleTest {

    @Test
    void exampleSerializationTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Path employeePath = Paths.get("src/test/resources/message.json");
        String employeeJson = Files.readString(employeePath);
        Example exampleData = objectMapper.readValue(employeeJson, Example.class);
        System.out.println(exampleData);
    }

    @Test
    void whenSerializingUsingJsonRootName_thenCorrect() throws JsonProcessingException {

        Source source = new Source();
        source.setId("1234");
        source.setReferenceNumber("refereenceId");

        Metadata metadata = new Metadata();
        metadata.setKey("key");
        metadata.setValue("SomeValue");

        List<Metadata> metaDatas = new ArrayList<>();
        metaDatas.add(metadata);

        Event event = new Event();
        event.setMetadata(metaDatas);
        event.setType("eventType");
        List<Event> events = new ArrayList<>();
        events.add(event);

        Example example = new Example();

        example.setEvents(events);
        example.setSource(source);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String result = objectMapper.writeValueAsString(example);

        System.out.println(result);
    }
    
    @Test
void example1(){
    JSONObject jo = new JSONObject();
    Map<String, String> m = new LinkedHashMap<String,String>();
    m.put("id", "ANTHEM");
    m.put("referenceNumber", "1234");
    jo.put("source",m);
    JSONArray ja= new JSONArray();
    m = new LinkedHashMap<String,String>();
    m.put("type", "auth_exchane");
    m.put("metadata", "");
    ja.add(m);
    jo.put("events",ja);
    System.out.println(jo);
}
}
