package ca.tango.livelearning.service;

import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.BreakpointFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Stack;

@Service
public class BreakpointListService {
    private Stack<Breakpoint> breakpoints = new Stack<>();


    @PostConstruct
    public void initBreakpoints() {
        BreakpointFactory.outOf5("first question?");
        BreakpointFactory.outOf5("second question?");
        BreakpointFactory.outOf5("third Question?");
    }

    public Breakpoint pop() {
        return breakpoints.pop();
    }
}
