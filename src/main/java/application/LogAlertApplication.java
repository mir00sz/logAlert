package application;

import application.dao.EventRepository;
import application.domain.Event;
import application.fileprocessing.EventLog;
import application.fileprocessing.JsonToEventLogMapper;
import application.fileprocessing.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@SpringBootApplication
public class LogAlertApplication implements CommandLineRunner {


    private final EventRepository eventRepository;

    private final JsonToEventLogMapper jsonToEventLogMapper;

    private static final int ALERT_TIME = 4;


    private static Logger LOG = LoggerFactory
            .getLogger(LogAlertApplication.class);

    @Autowired
    public LogAlertApplication(EventRepository eventRepository, JsonToEventLogMapper jsonToEventLogMapper) {
        this.eventRepository = eventRepository;
        this.jsonToEventLogMapper = jsonToEventLogMapper;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(LogAlertApplication.class, args);
        LOG.info("APPLICATION FINISHED");

    }


    @Override
    public void run(String... args) throws IOException {


        String path = args[0];
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
                        e.printStackTrace();
                    }
                }


        );

        LOG.info("finish");

    }
}
