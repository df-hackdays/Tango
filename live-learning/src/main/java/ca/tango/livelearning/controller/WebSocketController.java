package ca.tango.livelearning.controller;

import ca.tango.livelearning.domain.Breakpoint;
import ca.tango.livelearning.domain.ChatMessage;
import ca.tango.livelearning.service.BreakpointListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    BreakpointListService breakpointListService;


    @MessageMapping("/lecturer/send-breakpoint")
    @SendTo("/class")
    public Breakpoint sendBreakpoint() {
        return breakpointListService.pop();
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}