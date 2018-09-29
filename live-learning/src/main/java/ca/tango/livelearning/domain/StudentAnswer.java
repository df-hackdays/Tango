package ca.tango.livelearning.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class StudentAnswer {

    String questionId;
    String studentId;
    String answer;
    String isCorrectAnswer;
}
