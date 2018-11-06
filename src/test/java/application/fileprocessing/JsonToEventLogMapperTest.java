package application.fileprocessing;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsonToEventLogMapperTest {


    @Test
    public void shouldMapJsonToEventLog() throws IOException {
        String json = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\n" +
                "\"host\":\"12345\", \"timestamp\":1491377495212}";
        JsonToEventLogMapper jsonToEventLogMapper = new JsonToEventLogMapper();
        EventLog eventLog = jsonToEventLogMapper.jsonToEventLog(json);

        assertEquals(eventLog.getId(), "scsmbstgra");
        assertEquals(eventLog.getState().toString(), "STARTED");
        assertEquals(eventLog.getType(), "APPLICATION_LOG");
        assertEquals(eventLog.getHost(), "12345");
        assertEquals(eventLog.getTimestamp(), 1491377495212L);
    }

    @Test
    public void shouldMapJsonEvenIfHostIsEmpty() throws IOException {
        String json = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\n" +
                "\"timestamp\":1491377495212}";

        JsonToEventLogMapper jsonToEventLogMapper = new JsonToEventLogMapper();
        EventLog eventLog = jsonToEventLogMapper.jsonToEventLog(json);

        assertEquals(eventLog.getId(), "scsmbstgra");
        assertEquals(eventLog.getState().toString(), "STARTED");
        assertEquals(eventLog.getType(), "APPLICATION_LOG");
        assertNull(eventLog.getHost());
        assertEquals(eventLog.getTimestamp(), 1491377495212L);
    }


    @Test(expected = JsonMappingException.class)
    public void shouldThrowJsonMappingExceptionWhenNoId() throws IOException {
        String json = "{\"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\n" +
                "\"timestamp\":1491377495212}";
        JsonToEventLogMapper jsonToEventLogMapper = new JsonToEventLogMapper();
        EventLog eventLog = jsonToEventLogMapper.jsonToEventLog(json);


    }

    @Test(expected = JsonMappingException.class)
    public void shouldThrowJsonMappingExceptionWhenUnknownProperty() throws IOException {
        String json = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\n" +
                "\"unknownproperty\":1491377495212}";

        JsonToEventLogMapper jsonToEventLogMapper = new JsonToEventLogMapper();
        EventLog eventLog = jsonToEventLogMapper.jsonToEventLog(json);

    }



}