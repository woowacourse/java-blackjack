package blackjack;

import blackjack.config.AppConfig;
import blackjack.controller.BlackjackController;

public class Application {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        BlackjackController blackjackController = config.blackjackController();
        blackjackController.run();
    }
}
