package application;

import application.fileprocessing.JsonToEventLogMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {

    @Bean
    public JsonToEventLogMapper jsonToEventLogMapper() {
        return new JsonToEventLogMapper();

    }

}
