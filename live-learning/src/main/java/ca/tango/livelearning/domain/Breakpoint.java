package ca.tango.livelearning.domain;

import ca.tango.livelearning.enumeration.QuestionTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "breakpoint")
public class Breakpoint<T> {
    @Id
    Long questionId;
    QuestionTypeEnum questionTypeEnum;
    String question;
    List<T> options;
    boolean isIsGeneralFeedback;
}
