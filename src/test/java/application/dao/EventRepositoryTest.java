package application.dao;

import application.domain.Event;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigurationPackage
@EntityScan("application.*")
public class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;


    @Test
    public void shouldinsertAndRetrieveRow(){
        Event event1 = new Event();
        event1.setDuration(1);
        event1.setHost("host");
        event1.setType("type");
        event1.setId("id");
        event1.setEndTimestamp(2);
        event1.setStartTimestamp(1);
        eventRepository.findById("id").ifPresent(Assert::assertNotNull);
    }


}