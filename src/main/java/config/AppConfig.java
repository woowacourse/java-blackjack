package config;

import controller.BlackjackController;
import service.BlackjackService;

public class AppConfig {

    public BlackjackController blackjackController() {
        return new BlackjackController(blackjackService());
    }

    public BlackjackService blackjackService() {
        return new BlackjackService();
    }
}
