package ca.tango.livelearning.controller;

import ca.tango.livelearning.domain.StudentPair;
import ca.tango.livelearning.service.StudentPairingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ChesterBenington")
public class BreakpointWebsocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    StudentPairingService studentPairingService;

    @Scheduled(fixedRate = 1000*60*2)
    public List<StudentPair> pushBreakpoint() {
        List<StudentPair> studentPairs = studentPairingService.studentPairs();
        this.template.convertAndSend("/class", studentPairs);
        return studentPairs;
    }

}
