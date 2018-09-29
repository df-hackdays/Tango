package ca.tango.livelearning.domain;

import ca.tango.livelearning.enumeration.QuestionTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Breakpoint<T> {
    @Id
    String id;
    String question;
    List<T> options;
    long classId;
    boolean hasCorrectAnswer = false;
    QuestionTypeEnum questionTypeEnum;
}
