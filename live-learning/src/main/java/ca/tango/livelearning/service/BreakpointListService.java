package ca.tango.livelearning.service;

import ca.tango.livelearning.domain.AnswerBreakpoint;
import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.BreakpointFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

@Service
public class BreakpointListService {
    private static final Logger log = LoggerFactory.getLogger(BreakpointListService.class);
    private Stack<Breakpoint> breakpoints = new Stack<>();


    @PostConstruct
    public void initBreakpoints() throws IOException {


        List<AnswerBreakpoint> answerBreakpoints = null;
        try {
            Resource resource = new ClassPathResource("/breakpointList.json");
            answerBreakpoints = new ObjectMapper().readValue(resource.getFile(), new TypeReference<List<AnswerBreakpoint>>(){});
        } catch (IOException e) {
            log.error("could not load /breakpointList.json", e);
            throw new IOException(e);
        }
    }

    public Breakpoint pop() {
        if (breakpoints.size() > 0)
            return breakpoints.pop();
        return BreakpointFactory.feedbackGeneral();
    }
}
