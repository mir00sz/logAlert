package application.fileprocessing;

import application.dao.AlertRepository;
import org.springframework.stereotype.Service;

@Service
public class FileProcessor {


    private AlertRepository alertRepository;

    public FileProcessor(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void processFile(){

    }



}
