package ca.tango.livelearning.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class StudentAnswer<T> {
    @Id
    String id;
    Integer questionId;
    String studentId;
    T answer;
    Boolean isCorrectAnswer;
    Boolean isGenericQuestion;
}
