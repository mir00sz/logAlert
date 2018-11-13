package application;

import application.fileprocessing.FileProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@SpringBootApplication
@Profile("!test")
public class LogAlertApplication implements CommandLineRunner {

    private final FileProcessor fileProcessor;


    private static final Logger LOG = LoggerFactory
            .getLogger(LogAlertApplication.class);


    @Autowired
    public LogAlertApplication(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(LogAlertApplication.class, args);
        LOG.info("APPLICATION FINISHED");

    }


    @Override
    public void run(String... args) throws IOException {
        String path = args[0];
        fileProcessor.processFile(path);
    }
}
