package ca.tango.livelearning;

import ca.tango.livelearning.domain.AnswerBreakpoint;
import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.StudentAnswer;
import ca.tango.livelearning.enumeration.QuestionTypeEnum;
import ca.tango.livelearning.service.LiveLearningDatabaseService;
import ca.tango.livelearning.service.StudentPairingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class LiveLearningApplication {

    @Autowired
    private LiveLearningDatabaseService liveLearningDatabaseService;

    @Autowired
    private StudentPairingService studentPairingService;
    public static void main(String[] args) {

        SpringApplication.run(LiveLearningApplication.class, args);

    }

    @PostConstruct
    public void test() {
        StudentAnswer answer = new StudentAnswer();
        answer.setAnswer(3);
        answer.setQuestionId(1538252267232l);
        answer.setStudentId("Mark");
        liveLearningDatabaseService.insertStudentAnswer(answer);

        studentPairingService.studentPairs();
    }
}