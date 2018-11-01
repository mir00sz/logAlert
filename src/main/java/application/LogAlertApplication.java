package application;

import application.domain.Alert;
import application.dao.AlertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogAlertApplication implements CommandLineRunner {


    private final AlertRepository alertRepository;

    private static Logger LOG = LoggerFactory
            .getLogger(LogAlertApplication.class);

    @Autowired
    public LogAlertApplication(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(LogAlertApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }


    @Override
    public void run(String... args) {
        Alert alert = new Alert();
        //alert.setEventDuration(2);
        LOG.info("Start saving....");
        alertRepository.save(alert);

        Iterable<Alert> iterable = alertRepository.findAll();
        //iterable.forEach(j->System.out.println("received: " + j.getEventDuration()));

        LOG.info("finish");

    }
}
