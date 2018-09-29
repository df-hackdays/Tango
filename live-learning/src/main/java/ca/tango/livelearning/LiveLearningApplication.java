package ca.tango.livelearning;

import ca.tango.livelearning.service.LiveLearningDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LiveLearningApplication {

    @Autowired
    private LiveLearningDatabaseService liveLearningDatabaseService;

    public static void main(String[] args) {

        SpringApplication.run(LiveLearningApplication.class, args);

    }
}