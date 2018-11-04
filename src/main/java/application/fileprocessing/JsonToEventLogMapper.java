package application.fileprocessing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonToEventLogMapper {

    private ObjectMapper objectMapper;

    public JsonToEventLogMapper() {
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    public EventLog jsonToEventLog(String json) throws IOException {
        return objectMapper.readValue(json, EventLog.class);
    }

}
