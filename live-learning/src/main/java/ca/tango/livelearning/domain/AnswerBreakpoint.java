package ca.tango.livelearning.domain;

import lombok.Data;

/**
 * Only to be used by the lecturer since they need to show what the answer is
 */
@Data
public class AnswerBreakpoint<T> extends Breakpoint{
    T answer;
}
