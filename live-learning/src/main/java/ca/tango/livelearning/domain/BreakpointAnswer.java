package ca.tango.livelearning.domain;

import ca.tango.livelearning.enumeration.QuestionTypeEnum;
import lombok.Data;

@Data
public class BreakpointAnswer {
    String studentId;
    Object answer;
    QuestionTypeEnum type;
}
