package ca.tango.livelearning.controller;

import ca.tango.livelearning.domain.*;
import ca.tango.livelearning.service.BreakpointListService;
import ca.tango.livelearning.service.LiveLearningDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class WebSocketController {
    @Autowired
    BreakpointListService breakpointListService;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private LiveLearningDatabaseService liveLearningDatabaseService;

    @MessageMapping("/lecturer/send-breakpoint")
    @SendTo("/class")
    public Breakpoint sendBreakpoint() {
        return breakpointListService.pop();
    }

    @MessageMapping("/lecturer/send-feedback-breakpoint")
    @SendTo("/class")
    public Breakpoint sendFeedbackBreakpoint() {
        Breakpoint breakpoint = BreakpointFactory.feedbackGeneral();
        return breakpoint;
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

    @MessageMapping("/student/send-breakpoint-answer")
    @SendTo("/lecturer")
    public StudentAnswer answerBreakpoint(@Payload StudentAnswer answer, SimpMessageHeaderAccessor headerAccessor) {
        liveLearningDatabaseService.insertStudentAnswer(answer);
        return answer;
    }


    @MessageMapping("/student/{roomId}")
    private void sendMessageToPrivateTutoringSession(String message, @DestinationVariable String roomId) throws IOException {
        this.template.convertAndSend("/privateTutoringChat/" + roomId, message);
    }

    @MessageMapping("/student/send-question-to-lecturer")
    @SendTo("/lecturer")
    public StudentQuestion sendQuestionToLecturer(@Payload StudentQuestion studentQuestion) {
        return studentQuestion;
    }
}