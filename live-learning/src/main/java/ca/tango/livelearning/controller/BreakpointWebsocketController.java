package ca.tango.livelearning.controller;

import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.BreakpointFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/ChesterBenington")
public class BreakpointWebsocketController {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * to test websocket push from server
     * @return
     */
    @GetMapping("/lol")
    public Breakpoint pushBreakpoint(){
        Breakpoint breakpoint =  BreakpointFactory.feedbackGeneral();
        this.template.convertAndSend("/class", breakpoint);
        return breakpoint;
    }


}
