package ca.tango.livelearning.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Breakpoint {
    @Id
    String id;
    QuestionTypeEnum questionType;
    String questionId;
    String question;
    List<String> options;
}
