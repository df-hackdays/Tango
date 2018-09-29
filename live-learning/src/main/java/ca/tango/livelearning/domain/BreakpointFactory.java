package ca.tango.livelearning.domain;

import ca.tango.livelearning.enumeration.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BreakpointFactory {
    public static Breakpoint feedbackGeneral() {
        return outOf5("How would you rate your understanding of the material so far?");
    }

    public static Breakpoint outOf5(String question) {
        return oneOutOfN(question, 5);
    }

    public static Breakpoint oneOutOfN(String question, int n) {
        Breakpoint<Integer> breakpoint = new Breakpoint();
        breakpoint.setQuestion(question);
        breakpoint.setQuestionTypeEnum(QuestionTypeEnum.INTEGER);

        List<Integer> answers = new ArrayList<>();
        IntStream.range(1, n + 1).forEach(x -> answers.add(x));
        breakpoint.setOptions(answers);

        return breakpoint;
    }
}
