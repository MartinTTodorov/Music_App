package music_individual.demo.controller;

import music_individual.demo.domain.SentMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
class ChatControllerTest {




    @Test
    void testGreeting() throws Exception {
        // Arrange
        SentMessage sentMessage = new SentMessage("user", "hello");
        ChatController chatController = new ChatController();
        // Act
        SentMessage result = chatController.greeting(sentMessage);

        // Assert
        assertEquals("user", result.getUsername());
        assertEquals("hello", result.getContent());
    }


}