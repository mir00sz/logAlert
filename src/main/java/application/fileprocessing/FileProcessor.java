package application.fileprocessing;

import application.dao.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class FileProcessor {


    private EventRepository eventRepository;

    public FileProcessor(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void processFile(){

    }



}
