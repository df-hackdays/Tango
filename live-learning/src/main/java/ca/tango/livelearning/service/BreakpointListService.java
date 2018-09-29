package ca.tango.livelearning.service;

import ca.tango.livelearning.domain.AnswerBreakpoint;
import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.BreakpointFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BreakpointListService {

    @Autowired
    LiveLearningDatabaseService liveLearningDatabaseService;

    private static final Logger log = LoggerFactory.getLogger(BreakpointListService.class);
    private Stack<AnswerBreakpoint> breakpoints = new Stack<>();

    @PostConstruct
    public void initBreakpoints() throws IOException {


        try {
            Resource resource = new ClassPathResource("/breakpointList.json");
            breakpoints = new ObjectMapper().readValue(resource.getFile(), new TypeReference<Stack<AnswerBreakpoint>>(){});
            breakpoints.stream().forEach(x -> {
                x.setQuestionId(System.currentTimeMillis());
                liveLearningDatabaseService.insertBreakpoint(x);
            });
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
