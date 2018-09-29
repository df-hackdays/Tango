package ca.tango.livelearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LiveLearningApplication {

    public static void main(String[] args) {

        SpringApplication.run(LiveLearningApplication.class, args);

    }
}