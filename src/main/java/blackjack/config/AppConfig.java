package blackjack.config;

import blackjack.controller.BlackjackController;

public class AppConfig {
    public void run() {
        blackjackController().run();
    }

    private BlackjackController blackjackController() {
        return new BlackjackController();
    }
}