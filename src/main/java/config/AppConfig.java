package config;

import controller.BlackjackController;

public class AppConfig {
    public BlackjackController blackjackController() {
        return new BlackjackController();
    }
}
