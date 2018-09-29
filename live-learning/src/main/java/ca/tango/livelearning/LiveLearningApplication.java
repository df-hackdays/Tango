package ca.tango.livelearning;

import ca.tango.livelearning.domain.Breakpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class LiveLearningApplication {

    public static void main(String[] args) {

        SpringApplication.run(LiveLearningApplication.class, args);
    }

}