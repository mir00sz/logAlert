package application.fileprocessing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventLog {

    private String id;

    private State state;

    private long timestamp;

    private String type;

    private String host;

    @JsonCreator
    public EventLog(@JsonProperty(value = "id", required = true) String id,
                    @JsonProperty(value = "state") State state,
                    @JsonProperty(value = "timestamp") long timestamp,
                    @JsonProperty(value = "type") String type,
                    @JsonProperty(value = "host") String host) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
        this.type = type;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
