package music_individual.demo.controller;


import music_individual.demo.domain.SentMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public SentMessage greeting(SentMessage message) throws Exception {
        return new SentMessage(message.getUsername(), message.getContent());
    }
}
