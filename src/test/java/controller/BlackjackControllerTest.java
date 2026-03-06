package controller;

import org.junit.jupiter.api.BeforeEach;
import service.BlackjackService;

class BlackjackControllerTest {
    private BlackjackController blackjackController;
    private BlackjackService blackjackService;

    @BeforeEach
    void setUp(){
        BlackjackController blackjackController = new BlackjackController(blackjackService);
        this.blackjackController = blackjackController;
    }


}