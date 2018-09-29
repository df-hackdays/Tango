package ca.tango.livelearning.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BreakpointFactory {



    public static Breakpoint feedbackGeneral() {
        return outOf5("How would you rate your understanding of the material so far?");
    }

    public static Breakpoint outOf5(String question) {
        Breakpoint breakpoint = new Breakpoint();
        breakpoint.setQuestion(question);

        List<String> answers = new ArrayList<>();
        IntStream.range(1, 6).forEach(x -> answers.add(String.valueOf(x)));

        return breakpoint;
    }
}
