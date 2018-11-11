package application.fileprocessing;

import application.dao.EventRepository;
import application.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileProcessor {


    private static final Logger LOG = LoggerFactory
            .getLogger(FileProcessor.class);
    private static final long ALERT_TIME = 4;
    private final EventRepository eventRepository;
    private final JsonToEventLogMapper jsonToEventLogMapper;

    public FileProcessor(EventRepository eventRepository, JsonToEventLogMapper jsonToEventLogMapper) {
        this.eventRepository = eventRepository;
        this.jsonToEventLogMapper = jsonToEventLogMapper;
    }

    public void processFile(String path) throws IOException {
        Files.lines(Paths.get(path)).forEach(s -> {
                    try {
                        EventLog eventLog = jsonToEventLogMapper.jsonToEventLog(s);
                        Optional<Event> eventOptional = eventRepository.findById(eventLog.getId());
                        Event event = eventOptional.orElse(new Event());

                        if (eventLog.getState() == State.STARTED) {
                            event.setStartTimestamp(eventLog.getTimestamp());
                        } else {
                            event.setEndTimestamp(eventLog.getTimestamp());
                        }

                        if (event.getId() != null) {

                            long duration = event.getEndTimestamp() - event.getStartTimestamp();
                            event.setDuration(duration);
                            if (duration > ALERT_TIME) {
                                event.setAlert(true);
                            }
                        } else {
                            event.setId(eventLog.getId());
                            event.setType(eventLog.getType());
                            event.setHost(eventLog.getHost());
                        }

                        eventRepository.save(event);

                    } catch (IOException e) {
                        LOG.error(e.getMessage());
                    }
                }


        );

    }



}
