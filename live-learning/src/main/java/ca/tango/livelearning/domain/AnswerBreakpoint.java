package ca.tango.livelearning.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Only to be used by the lecturer since they need to show what the answer is
 */
@Data
@Document(collection = "breakpoint")
public class AnswerBreakpoint extends Breakpoint{
    String answer;
}
