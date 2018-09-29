package ca.tango.livelearning;

import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.StudentAnswer;
import ca.tango.livelearning.enumeration.QuestionTypeEnum;
import ca.tango.livelearning.service.LiveLearningDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class LiveLearningApplication {

    @Autowired
    private LiveLearningDatabaseService liveLearningDatabaseService;
    public static void main(String[] args) {

        SpringApplication.run(LiveLearningApplication.class, args);

    }

    @PostConstruct
    public void test() {
        StudentAnswer answer = new StudentAnswer();
        answer.setAnswer("HTML");
        answer.setQuestionId("123");
        answer.setStudentId("abc");
        liveLearningDatabaseService.insertStudentAnswer(answer);

        Breakpoint breakpoint = new Breakpoint();
        breakpoint.setQuestion("Some question");
        breakpoint.setQuestionTypeEnum(QuestionTypeEnum.INTEGER);
        breakpoint.setQuestionId("xyz");
        liveLearningDatabaseService.insertBreakpoint(breakpoint);
    }

}