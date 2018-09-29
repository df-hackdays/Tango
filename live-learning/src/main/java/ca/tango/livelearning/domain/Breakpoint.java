package ca.tango.livelearning.domain;

import ca.tango.livelearning.enumeration.QuestionTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class Breakpoint {
    String question;
    List<String> options;
    long programId;
    boolean hasCorrectAnswer = false;
    QuestionTypeEnum questionTypeEnum;
}
